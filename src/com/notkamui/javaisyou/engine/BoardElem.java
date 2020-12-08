package com.notkamui.javaisyou.engine;

public class BoardElem {
    private Noun noun;
    Direction direction;
    private final Board board;
    private boolean isAlive = true;
    private int x;
    private int y;

    public BoardElem(Board board) {
        this.board = board;
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }

    Noun noun() {
        return noun;
    }

    boolean isAlive() {
        return isAlive;
    }

    void setAlive(boolean state) {
        isAlive = state;
    }

    boolean hasFlag(Flag flag) {
        return noun.hasFlag(flag);
    }

    void addFlag(Flag flag) {
        noun.addFlag(flag);
    }

    void removeFlag(Flag flag) {
        noun.removeFlag(flag);
    }

    private boolean applyProperties(BoardElem other, Movement move) {
        for (var prop : this.noun.properties()) {
            if (!prop.apply(this, other, move))
                return false;
        }
        return true;
    }

    boolean move(Movement move) {
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
