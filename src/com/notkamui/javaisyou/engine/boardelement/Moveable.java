package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;

public interface Moveable extends LocatedObject {
    boolean move(Movement move);
}
