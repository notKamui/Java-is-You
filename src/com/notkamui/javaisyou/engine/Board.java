package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.Applicable;
import com.notkamui.javaisyou.engine.boardelement.GameObject;
import com.notkamui.javaisyou.engine.boardelement.Noun;
import com.notkamui.javaisyou.engine.boardelement.Operator;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {
    private final int width;
    private final int height;
    private final List<GameObject> gameObjects = new ArrayList<>();
    private List<Rule> activeRules = new ArrayList<>();

    public Board(String levelPath) {
        Objects.requireNonNull(levelPath);
        // TODO parse .txt and create required entities and types

        width = 10;
        height = 10;

    }

    public List<GameObject> get(int x, int y) {
        return gameObjects.stream()
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
        gameObjects.stream()
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

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public String toString() {
        return "Board{" +
                "\nwidth=" + width +
                "\nheight=" + height +
                "\nentities=" + gameObjects +
                '}';
    }
}

