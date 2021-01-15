package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.LocatedObject;
import com.notkamui.javaisyou.engine.rule.RightOperandType;
import com.notkamui.javaisyou.engine.rule.Rule;
import com.notkamui.javaisyou.utils.GameStatus;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A class that represents and manages ONE level and can manipulate its state.
 * A LevelManager knows the width and height of the board
 * and contains a DisplayManager and a RuleManager in addition to the Model of the level.
 */
public final class LevelManager implements MovementObserver {
  private final int width;
  private final int height;
  private final DisplayManager displayManager;
  private final Model model;
  private final RuleManager ruleManager;

  /**
   * Constructor for the LevelManager
   *
   * @param width         the width of the board
   * @param height        the height of the board
   * @param boardElements the list of board elements of the level
   * @param defaultRules  the list of default rules (that cannot be broken)
   */
  public LevelManager(int width, int height, List<BoardElement> boardElements, List<Rule> defaultRules) {
    Objects.requireNonNull(boardElements);
    Objects.requireNonNull(defaultRules);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("width and height must be positives");
    }
    this.width = width;
    this.height = height;
    this.model = new Model(boardElements);
    this.ruleManager = new RuleManager(model, defaultRules);
    this.displayManager = new DisplayManager(this.model, width, height);
  }

  /**
   * Renders and displays a frame
   *
   * @param graphics     the graphic engine zone
   * @param x            the x coordinate of the top left corner
   * @param y            the y coordinate of the top left corner
   * @param windowWidth  the width of the viewport
   * @param windowHeight the height of the viewport
   */
  public void displayGame(Graphics2D graphics, int x, int y, int windowWidth, int windowHeight) {
    displayManager.display(graphics, x, y, windowWidth, windowHeight);
  }

  private void applySuperpositionRules(BoardElement first, BoardElement second) {
    if (first.state() && second.state()) {
      var rules = ruleManager.rulesOf(first.type());
      rules.addAll(ruleManager.rulesOf(second.type()));
      rules.forEach(rule -> rule.onSuperposition(first, second, ruleManager));
    }
  }

  private void updateElement(BoardElement element) {
    model.elementsAt(element.x(), element.y()).forEach(other -> {
      applySuperpositionRules(element, other);
    });
    if (!element.state()) {
      ruleManager.rulesOf(element.type()).forEach(r -> r.onDeath(element, model));
    }
  }

  private void updateElements() {
    model.elements().forEach(this::updateElement);
  }

  /**
   * Updates the state of the game.
   * - Updates the rules
   * - Applies each rule
   * - Updates the state of each elements
   * - Removes all dead elements from the board
   */
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
        for (var otherRule : ruleManager.rulesOf(other.type())) {
          var canMove = otherRule.onMove(movingElement, other, ruleManager, move, this);
          if (!canMove) {
            return false;
          }
        }
      }
    }
    movingElement.move(move);
    return true;
  }

  /**
   * Moves all elements with the property YOU  with a given Direction.
   *
   * @param direction the direction of the movement
   */
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

  /**
   * Checks the status of the game
   *
   * @return the current GameStatus of the game (ONGOING, WIN or LOSE)
   */
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
    return model.elementsFiltered(e -> ruleManager.hasProperty(type, e.type()));
  }
}

