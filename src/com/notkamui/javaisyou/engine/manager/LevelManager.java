package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.boardelement.LocatedObject;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.element.Word;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.OperationResult;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyType;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;
import com.notkamui.javaisyou.utils.GameStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LevelManager implements MovementObserver {
    private final int width;
    private final int height;
    private final DisplayManager displayManager;
    private List<Rule> activeRules = new ArrayList<>();
    private final Model model;

    public LevelManager(int width, int height, List<EntityWrapper> entityWrappers, WordWrapper wordWrapper) {
        Objects.requireNonNull(entityWrappers);
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must be positives");
        }
        this.width = width;
        this.height = height;
        this.model = new Model(entityWrappers, wordWrapper);
        this.displayManager = new DisplayManager(this.model, width, height);
    }

    public void displayGame(Graphics2D graphics, int x, int y, int windowWidth, int windowHeight) {
        displayManager.display(graphics, x, y, windowWidth, windowHeight);
    }

    public void update() {
        updateRules();
        applyPassiveProperties();
        removeAllDead();
    }

    private void removeAllDead() {
        model.removeAllDead();
    }

    private void applyPassiveProperties() {
        model.elements()
                .forEach(e -> e.passiveProperties()
                        .stream()
                        .sorted(Comparator.comparingInt(Property::priority))
                        .forEach(p -> {
                            var els = model.getElements(e.x(), e.y());
                            for (var left : els) {
                                for (var right : els) {
                                        p.applyPassive(left, right);
                                }
                            }
                        })
                );
    }

    private List<Rule> buildRules(List<Word> leftList, Operator operator, List<Word> rightList) {
        var newRules = new ArrayList<Rule>();
        leftList.forEach(left -> rightList.forEach(right -> {
            newRules.add(new Rule(left.getAsLeft(), operator, right.getAsRight()));
        }));
        return newRules;
    }

    private List<Rule> findRules(Operator operator, int x, int y) {
        var leftList = model.getWords(x, y - 1);
        var rightList = model.getWords(x, y + 1);
        var upList = model.getWords(x - 1, y);
        var downList = model.getWords(x + 1, y);

        var foundRules = new ArrayList<Rule>();
        if (!leftList.isEmpty() && !rightList.isEmpty()) {
            foundRules.addAll(buildRules(leftList, operator, rightList));
        }
        if (!upList.isEmpty() && !downList.isEmpty()) {
            foundRules.addAll(buildRules(upList, operator, downList));
        }
        return foundRules;
    }

    private List<Rule> newRules() {
        var newRules = new ArrayList<Rule>();
        for (var x = 0; x < width; x++) {
            for (var y = 0; y < height; y++) {
                var operators = model.getWords(x, y);
                if (!operators.isEmpty()) {
                    for (var operator : operators) {
                        newRules.addAll(findRules(operator.getAsOperator(), x, y));
                    }
                }
            }
        }
        return newRules;
    }

    private void applyNewRules(List<Rule> newRules) {
        var it = newRules.iterator();
        while (it.hasNext()) {
            var rule = it.next(); // applying the rule
            if (!activeRules.contains(rule)) {
                var res = rule.apply();
                if (res == OperationResult.INEFFECTIVE) {
                    it.remove(); // removing ineffective rules
                }
            }
        }
    }

    private void setNewRules(List<Rule> newRules) {
        activeRules.stream()
                .filter(r -> !newRules.contains(r))
                .forEach(Rule::unapply);
        activeRules = newRules;
    }

    private void updateRules() {
        var newRules = newRules();
        applyNewRules(newRules);// apply new rules and remove ineffective rules
        setNewRules(newRules);
    }

    @Override
    public boolean tryToMove(BoardElement movingObject, Movement move) {
        Objects.requireNonNull(movingObject);
        Objects.requireNonNull(move);

        var destX = movingObject.x() + move.vectX();
        var destY = movingObject.y() + move.vectY();

        if (destX < 0 || destX >= width || destY < 0 || destY >= height) {
            return false;
        }

        var others = model.getElements(destX, destY);
        if (!others.isEmpty()) {
            for (var other : others) {
                if (!applyMoveProperties(movingObject, other, move))
                    return false;
            }
        }
        movingObject.move(move);
        return true;
    }

    public void moveYou(Direction direction) {
        var move = switch (direction) {
            case NORTH -> new Movement(0, -1);
            case SOUTH -> new Movement(0, 1);
            case WEST -> new Movement(-1, 0);
            case EAST -> new Movement(1, 0);
        };
        var yous = sortByDirection(getWithFlag(PropertyType.YOU), direction);
        yous.forEach(you -> tryToMove(you, move));
    }

    private List<BoardElement> sortByDirection(List<BoardElement> youList, Direction direction) {
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

    private boolean applyMoveProperties(BoardElement movingObject, BoardElement receiver, Movement move) {
        for (var recProp : receiver.movementProperties()) {
            if (!recProp.applyOnMoveReceiver(movingObject, receiver, move, this))
                return false;
        }
        for (var trigProp : movingObject.movementProperties()) {
            if (!trigProp.applyOnMoveTrigger(movingObject, receiver, move, this))
                return false;
        }
        return true;
    }

    public GameStatus checkGameStatus() {
        var yous = getWithFlag(PropertyType.YOU);
        if (yous.isEmpty()) {
            return GameStatus.LOSE;
        } else {
            var wins = getWithFlag(PropertyType.WIN);
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

    private List<BoardElement> getWithFlag(PropertyType flag) {
        return model.elements()
                .stream()
                .filter(e -> e.flags().contains(flag))
                .collect(Collectors.toList());
    }
}

