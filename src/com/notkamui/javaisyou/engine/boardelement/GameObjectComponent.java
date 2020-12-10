package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Board;
import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.Movement;

import java.util.Objects;

public class GameObjectComponent implements Moveable, Stateable {
    Direction direction;
    private final Board board;
    private boolean isAlive = true;
    private final GameObject self;
    private int x;
    private int y;

    public GameObjectComponent(Board board, GameObject self, Direction dir, int x, int y) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(dir);
        Objects.requireNonNull(self);
        this.board = board;
        this.self = self;
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

    private static boolean applyProperties(GameObject trigger, GameObject receiver, Movement move) {
        for (var trigProp : trigger.properties()) {
            if (!trigProp.apply(trigger, receiver, move))
                return false;
        }
        for (var recProp : receiver.properties()) {
            if (!recProp.apply(trigger, receiver, move))
                return false;
        }
        return true;
    }

    @Override
    public boolean move(Movement move) {
        var destX = x + move.vectX();
        var destY = y + move.vectY();

        if (destX < 0 || destX >= board.width() || destY < 0 || destY >= board.height()) return false;

        var others = board.get(destX, destY);
        if (!others.isEmpty()) {
            for (var other : others) {
                if (!applyProperties(self, other, move))
                    return false;
            }
        }
        x += move.vectX();
        y += move.vectY();

        return true;
    }

}
