package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.Applicable;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.Noun;
import com.notkamui.javaisyou.engine.boardelement.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameManager implements MovementObserver {
    private final int width;
    private final int height;
    private final List<BoardElement> boardElements = new ArrayList<>();
    private List<Rule> activeRules = new ArrayList<>();

    public GameManager(String levelPath) {
        Objects.requireNonNull(levelPath);
        // TODO parse .txt and create required entities and types

        width = 10;
        height = 10;
    }

    private List<BoardElement> get(int x, int y) {
        return boardElements.stream()
                .filter(e -> e.x() == x && e.y() == y)
                .collect(Collectors.toList());
    }

    private Optional<Noun> getNoun(int x, int y) {
        return get(x, y)
                .stream()
                .filter(e -> e instanceof Noun)
                .map(e -> (Noun) e)
                .findFirst();
    }

    private Optional<Applicable> getApplicable(int x, int y) {
        return get(x, y)
                .stream()
                .filter(e -> e instanceof Applicable)
                .map(e -> (Applicable) e)
                .findFirst();
    }

    private List<Rule> newRules() {
        var newRules = new ArrayList<Rule>();
        boardElements.stream()
                .filter(go -> go instanceof Operator)
                .forEach(op -> {
                    final var left = getNoun(op.x(), op.y()-1);
                    final var right = getApplicable(op.x(), op.y()+1);
                    final var up = getNoun(op.x()-1, op.y());
                    final var down = getApplicable(op.x()+1, op.y());

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

        var others = get(destX, destY);
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
        return "GameManager{" +
                "\nwidth=" + width +
                "\nheight=" + height +
                "\nentities=" + boardElements +
                '}';
    }
}

