package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.Movement;

import java.util.Objects;

public class GameObjectComponent implements Moveable, Stateable {
    Direction direction;
    private boolean isAlive = true;
    private int x;
    private int y;

    public GameObjectComponent(Direction dir, int x, int y) {
        Objects.requireNonNull(dir);
        this.direction = dir;
        this.x = x;
        this.y = y;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public boolean state() {
        return isAlive;
    }

    @Override
    public void setState(boolean state) {
        isAlive = state;
    }

    @Override
    public void move(Movement move) {
        x += move.vectX();
        y += move.vectY();
    }

}
