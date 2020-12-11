package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;

public interface MovementObserver {
  boolean tryToMove(BoardElement movingObject, Movement move);
}
