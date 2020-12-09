package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.*;
import com.notkamui.javaisyou.engine.type.EntityWrapper;

import java.util.Objects;

public class Entity {
    private EntityWrapper entityWrapper;
    Direction direction;
    private final Board board;
    private boolean isAlive = true;
    private int x;
    private int y;

    public Entity(Board board, int x, int y) {
        Objects.requireNonNull(board);
        this.board = board;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    EntityWrapper noun() {
        return entityWrapper;
    }

    boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean state) {
        isAlive = state;
    }

    public boolean hasFlag(Flag flag) {
        return entityWrapper.hasFlag(flag);
    }

    void addFlag(Flag flag) {
        entityWrapper.addFlag(flag);
    }

    void removeFlag(Flag flag) {
        entityWrapper.removeFlag(flag);
    }

    private boolean applyProperties(Entity other, Movement move) {
        for (var prop : this.entityWrapper.properties()) {
            if (!prop.apply(this, other, move))
                return false;
        }
        return true;
    }

    public boolean move(Movement move) {
        var destX = x + move.vectX();
        var destY = y + move.vectY();

        if (destX < 0 || destX >= board.width() || destY < 0 || destY >= board.height()) return false;

        var others = board.get(destX, destY);
        if (!others.isEmpty()) {
            var stop = false;
            for (var be : others) {
                if (!be.applyProperties(this, move))
                    return false;
                else if (!this.applyProperties(be, move))
                    return false;
            }
        } else {
            x += move.vectX();
            y += move.vectY();
        }
        return true;
    }
}
