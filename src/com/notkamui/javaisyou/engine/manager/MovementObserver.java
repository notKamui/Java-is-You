package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;

public interface MovementObserver {
  boolean tryToMove(BoardElement movingElement, Movement move);
}
