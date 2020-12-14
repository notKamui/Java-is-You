package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;

public interface MovementObserver {
  boolean tryToMove(BoardElement movingObject, Movement move);
}
