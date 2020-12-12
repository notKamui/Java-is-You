package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.MovementObserver;
import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.boardelement.Applicable;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.Noun;
import com.notkamui.javaisyou.engine.boardelement.Operator;
import com.notkamui.javaisyou.engine.type.EntityWrapper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class LevelManager implements MovementObserver {
    private final int width;
    private final int height;
    private final List<BoardElement> boardElements;
    private List<Rule> activeRules = new ArrayList<>();
    private final Model model;

    public LevelManager(int width, int height, List<BoardElement> boardElements) {
        Objects.requireNonNull(boardElements);
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must be positives");
        }
        this.width = width;
        this.height = height;
        this.boardElements = new ArrayList<>(boardElements);
        this.model = new Model(boardElements);
    }

    private List<Rule> newRules() {
        var newRules = new ArrayList<Rule>();
        boardElements.stream()
                .filter(go -> go instanceof Operator)
                .forEach(op -> {
                    final var left = model.getNoun(op.x(), op.y()-1);
                    final var right = model.getApplicable(op.x(), op.y()+1);
                    final var up = model.getNoun(op.x()-1, op.y());
                    final var down = model.getApplicable(op.x()+1, op.y());

                    if (left.isPresent() && right.isPresent()) {
                        newRules.add(new Rule(left.get(), (Operator)op, right.get()));
                    }
                    if (up.isPresent() && down.isPresent()) {
                        newRules.add(new Rule(up.get(), (Operator)op, down.get()));
                    }
                });
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
                "\nentities-count=" + boardElements.size() +
                "\n}";
    }
}

