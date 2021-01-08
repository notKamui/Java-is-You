package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.boardelement.LocatedObject;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.rule.RightOperandType;
import com.notkamui.javaisyou.utils.GameStatus;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LevelManager implements MovementObserver {
  private final int width;
  private final int height;
  private final DisplayManager displayManager;
  private final Model model;
  private final RuleManager ruleManager;

  public LevelManager(int width, int height, List<BoardElement> boardElements) {
    Objects.requireNonNull(boardElements);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("width and height must be positives");
    }
    this.width = width;
    this.height = height;
    this.model = new Model(boardElements);
    this.ruleManager = new RuleManager(model);
    this.displayManager = new DisplayManager(this.model, width, height);
  }

  public void displayGame(Graphics2D graphics, int x, int y, int windowWidth, int windowHeight) {
    displayManager.display(graphics, x, y, windowWidth, windowHeight);
  }

  private void applySuperpositionRules(BoardElement first, BoardElement second) {
    ruleManager.rulesOf(first.id()).forEach(rule -> rule.onSuperposition(first, second, ruleManager));
  }

  private void updateElements() {
    model.elements().forEach(first -> {
      model.elementsAt(first.x(), first.y()).forEach(second -> {
        applySuperpositionRules(first, second);
        applySuperpositionRules(second, first);
      });
    });
  }

  public void update() {
    ruleManager.update();
    ruleManager.rules().forEach(rule -> rule.onRuleCreation(model));
    updateElements();
    model.removeAllDead();
  }

  @Override
  public boolean tryToMove(BoardElement movingElement, Movement move) {
    Objects.requireNonNull(movingElement);
    Objects.requireNonNull(move);

    var destX = movingElement.x() + move.vectorX();
    var destY = movingElement.y() + move.vectorY();

    if (destX < 0 || destX >= width || destY < 0 || destY >= height) {
      return false;
    }

    var others = model.elementsAt(destX, destY);
    if (!others.isEmpty()) {
      for (var other : others) {
        var otherRules = ruleManager.rulesOf(other.id());
        otherRules.forEach(r -> r.onMove(movingElement, other, ruleManager, move, this));
      }
    }
    movingElement.move(move);
    return true;
  }

  public void moveYou(Direction direction) {
    var move = switch (direction) {
      case NORTH -> new Movement(0, -1);
      case SOUTH -> new Movement(0, 1);
      case WEST -> new Movement(-1, 0);
      case EAST -> new Movement(1, 0);
    };
    var yous = sortByDirection(getElementsWithProperty(RightOperandType.YOU), direction);
    yous.forEach(you -> tryToMove(you, move));
  }

  private static List<BoardElement> sortByDirection(List<BoardElement> youList, Direction direction) {
    Comparator<BoardElement> comp;
    if (direction == Direction.NORTH || direction == Direction.SOUTH) {
      comp = Comparator.comparingInt(LocatedObject::y);
    } else {
      comp = Comparator.comparingInt(LocatedObject::x);
    }
    if (direction == Direction.SOUTH || direction == Direction.EAST) {
      comp = comp.reversed();
    }
    return youList.stream().sorted(comp).collect(Collectors.toList());
  }

  public GameStatus checkGameStatus() {
    var yous = getElementsWithProperty(RightOperandType.YOU);
    if (yous.isEmpty()) {
      return GameStatus.LOSE;
    } else {
      var wins = getElementsWithProperty(RightOperandType.WIN);
      for (var you : yous) {
        for (var win : wins) {
          if (you.x() == win.x() && you.y() == win.y()) {
            return GameStatus.WIN;
          }
        }
      }
      return GameStatus.ONGOING;
    }
  }

  private List<BoardElement> getElementsWithProperty(RightOperandType type) {
    return model.elements()
            .stream()
            .filter(e -> ruleManager.hasProperty(type, e.id()))
            .collect(Collectors.toList());
  }
}

