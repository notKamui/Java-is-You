package com.notkamui.javaisyou.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    private final int width;
    private final int height;
    private final List<BoardElem> elems = new ArrayList<>();
    private final List<Noun> nouns = new ArrayList<>();

    public Board(int width, int height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("width and height must be positive");
        this.width = width;
        this.height = height;
    }

    void add(BoardElem elem) {
        Objects.requireNonNull(elem);
        elems.add(elem);
    }

    public List<BoardElem> get(int x, int y) {
        return elems.stream()
                .filter(e -> e.x() == x && e.y() == y)
                .collect(Collectors.toList());
    }

    int width() {
        return width;
    }

    int height() {
        return height;
    }
}

