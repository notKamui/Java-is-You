package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.operation.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LevelManager implements MovementObserver {
    private final int width;
    private final int height;
    //private final List<BoardElement> boardElements;
    private List<Rule> activeRules = new ArrayList<>();
    private final Model model;

    public LevelManager(int width, int height, List<BoardElement> boardElements) {
        Objects.requireNonNull(boardElements);
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must be positives");
        }
        this.width = width;
        this.height = height;
        //this.boardElements = new ArrayList<>(boardElements);
        this.model = new Model(boardElements);
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
        if (!upList.isEmpty() &&  !downList.isEmpty()) {
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

    public void updateRules() {
        var newRules = newRules();
        newRules.forEach(r -> {
            if (!activeRules.contains(r))
                r.apply();
        });
        activeRules.stream()
                .filter(r -> !newRules.contains(r))
                .forEach(Rule::unapply);
        activeRules = newRules;
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

    private boolean applyMoveProperties(BoardElement movingObject, BoardElement receiver, Movement move) {
        for (var trigProp : movingObject.movementProperties()) {
            if (!trigProp.applyOnMove(movingObject, receiver, move, this))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LevelManager{" +
                "\nwidth=" + width +
                "\nheight=" + height +
                //"\nentities-count=" + boardElements.size() +
                "\n}";
    }
}

