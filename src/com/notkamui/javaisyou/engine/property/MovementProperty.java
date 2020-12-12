package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.MovementObserver;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;

import java.util.Set;

public sealed interface MovementProperty extends Property {
  boolean applyOnMove(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer);

  record Push() implements MovementProperty {
    @Override
    public boolean applyOnMove(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer) {
      return observer.tryToMove(receiver, movement);
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }

    @Override
    public int priority() {
      return 0;
    }
  }

  record Stop() implements MovementProperty {
    @Override
    public boolean applyOnMove(BoardElement trigger, BoardElement receiver, Movement move, MovementObserver observer) {
      return false;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }

    @Override
    public int priority() {
      return 1;
    }
  }

}
