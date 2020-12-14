package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.boardelement.HasDirection;
import com.notkamui.javaisyou.engine.boardelement.Moveable;
import com.notkamui.javaisyou.engine.boardelement.Stateable;

import java.util.Objects;

public class BasicElementComponent implements Moveable, Stateable, HasDirection {
    Direction direction;
    private boolean isAlive = true;
    private int x;
    private int y;

    public BasicElementComponent(Direction dir, int x, int y) {
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
        // TODO update direction on move
        /*direction = switch (move) {
            case move.vectX() > 0 -> Direction.EAST;
            case move.vectX() < 0 -> Direction.WEST;
            case move.vectY() > 0 -> Direction.SOUTH;
            case move.vectY() < 0 -> Direction.NORTH;
        };*/
        x += move.vectX();
        y += move.vectY();
    }

    @Override
    public Direction direction() {
        return direction;
    }
}
