package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;

public interface Moveable extends LocatedObject {
    void move(Movement move);
}
