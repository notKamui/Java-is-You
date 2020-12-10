package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.*;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;
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

        // types
        var wallWrapper = new EntityWrapper(Sprites.WALL, Sprites.WALL_NOUN);
        var textWrapper = new WordWrapper();

        // property
        var pushProperty = new Property.Push();

        // entities
        gameObjects.add(new Entity(this, wallWrapper, Direction.NORTH, 5, 5));

        // words
        var textWall = new Noun(this, textWrapper, Direction.NORTH, 0, 0, wallWrapper);
        gameObjects.add(textWall);
        gameObjects.add(new IsOperator(this, textWrapper, Direction.NORTH, 1, 0));
        gameObjects.add(new TextualProperty(this, textWrapper, Direction.NORTH, 2, 0, pushProperty));

        System.out.println("Before updates: " + activeRules);
        updateRules();
        System.out.println("First update: " + activeRules);
        textWall.move(new Movement(1, 0));
        updateRules();
        System.out.println("Second update: " + activeRules);
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

