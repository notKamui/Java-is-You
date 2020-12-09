package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.Entity;
import com.notkamui.javaisyou.engine.boardelement.Sprites;
import com.notkamui.javaisyou.engine.type.EntityWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    private final int width;
    private final int height;
    private final List<Entity> entities = new ArrayList<>();
    //private final List<EntityWrapper> babaEntityTypes = new ArrayList<>();

    public Board(String levelPath) {
        Objects.requireNonNull(levelPath);
        // TODO parse .txt and create required entities and types

        width = 10;
        height = 10;

    }

    public List<Entity> get(int x, int y) {
        return entities.stream()
                .filter(e -> e.x() == x && e.y() == y)
                .collect(Collectors.toList());
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
                "\nentities=" + entities +
                '}';
    }
}

