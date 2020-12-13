package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.Movement;

public interface MovementObserver {
  boolean tryToMove(BoardElement movingObject, Movement move);
}
