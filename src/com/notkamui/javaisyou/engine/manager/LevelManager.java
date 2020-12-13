package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.utils.GameStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LevelManager implements MovementObserver {
    private final int width;
    private final int height;
    private final DisplayManager displayManager;
    private List<Rule> activeRules = new ArrayList<>();
    private final Model model;

    public LevelManager(int width, int height, List<BoardElement> boardElements) {
        Objects.requireNonNull(boardElements);
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must be positives");
        }
        this.width = width;
        this.height = height;
        this.model = new Model(boardElements);
        this.displayManager = new DisplayManager(this.model, width, height);
    }

    public void displayGame(Graphics2D graphics, int x, int y, int windowWidth, int windowHeight) {
        displayManager.display(graphics, x, y, windowWidth, windowHeight);
    }


    private List<Rule> buildRules(List<BoardElement> leftList, Operator operator, List<BoardElement> rightList) {
        var newRules = new ArrayList<Rule>();
        leftList.forEach(left -> {
            rightList.forEach(right -> {
                newRules.add(new Rule(left.getAsLeft(), operator, right.getAsRight()));
            });
        });
        return newRules;
    }

    private List<Rule> findRules(Operator operator, int x, int y) {
        var leftList = model.get(x, y - 1);
        var rightList = model.get(x, y + 1);
        var upList = model.get(x - 1, y);
        var downList = model.get(x + 1, y);

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
                var operators = model.get(x, y);
                if (!operators.isEmpty()) {
                    for (var operator : operators) {
                        newRules.addAll(findRules(operator.getAsOperator(), x, y));
                    }
                }
            }
        }
        return newRules;
    }

    private List<Rule> applyNewRules(List<Rule> newRules) {
        var it = newRules.iterator();
        while (it.hasNext()) {
            var rule = it.next(); // applying the rule
            var res = rule.apply();
            switch (res) {
                case INEFFECTIVE:
                    it.remove(); // removing ineffective rules
                    break;
                case TEXT_TO_ENTITY:
                    textToEntity(rule); // conversion
                    break;
                case ENTITY_TO_TEXT:
                    entityToText(rule); // conversion
                    break;
                default:
                    break;
            }
        }
        return newRules;
    }

    private void setNewRules(List<Rule> newRules) {
        activeRules.stream()
                .filter(r -> !newRules.contains(r))
                .forEach(Rule::unapply);
        activeRules = newRules;
    }

    public void updateRules() {
        var newRules = newRules();
        applyNewRules(newRules); // apply new rules and remove ineffective rules
        setNewRules(newRules);
    }

    private void textToEntity(Rule rule) {
    }

    private void entityToText(Rule rule) {
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

        var others = model.get(destX, destY);
        if (!others.isEmpty()) {
            for (var other : others) {
                if (!applyMoveProperties(movingObject, other, move))
                    return false;
            }
        }
        movingObject.move(move);
        return true;
    }

    public void moveYou(Direction direction) { // TODO prioritization by direction
        var move = switch (direction) {
            case NORTH -> new Movement(0, -1);
            case SOUTH -> new Movement(0, 1);
            case WEST -> new Movement(-1, 0);
            case EAST -> new Movement(1, 0);
        };
        getWithFlag(PropertyFlag.YOU).forEach(you -> tryToMove(you, move));
    }

    private boolean applyMoveProperties(BoardElement movingObject, BoardElement receiver, Movement move) {
        for (var trigProp : movingObject.movementProperties()) {
            if (!trigProp.applyOnMove(movingObject, receiver, move, this))
                return false;
        }
        return true;
    }

    public GameStatus checkGameStatus() {
        var yous = getWithFlag(PropertyFlag.YOU);
        if (yous.isEmpty()) {
            return GameStatus.LOSE;
        } else {
            var wins = getWithFlag(PropertyFlag.WIN);
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

    private List<BoardElement> getWithFlag(PropertyFlag flag) {
        return model.elements()
                .stream()
                .filter(e -> e.flags().contains(flag))
                .collect(Collectors.toList());
    }
}

