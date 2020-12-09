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

    public Entity(Board board, EntityWrapper entityWrapper, Direction dir, int x, int y) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(entityWrapper);
        Objects.requireNonNull(dir);
        this.board = board;
        this.entityWrapper = entityWrapper;
        this.direction = dir;
        this.x = x;
        this.y = y;
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


    private static boolean applyProperties(Entity trigger, Entity receiver, Movement move) {
        for (var trigProp : trigger.entityWrapper.properties()) {
            if (!trigProp.apply(trigger, receiver, move))
                return false;
        }
        for (var recProp : receiver.entityWrapper.properties()) {
            if (!recProp.apply(trigger, receiver, move))
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
            for (var other : others) {
                if (!applyProperties(this, other, move))
                    return false;
            }
        }
        x += move.vectX();
        y += move.vectY();

        return true;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "\ndirection=" + direction +
                "\nisAlive=" + isAlive +
                "\nx=" + x +
                "\ny=" + y +
                "\nentityWrapper=" + entityWrapper +
                '}';
    }
}
