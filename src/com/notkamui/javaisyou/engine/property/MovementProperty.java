package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Result;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public sealed interface MovementProperty extends Property {
  boolean applyOnMove(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer);

  record Push() implements MovementProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/PUSH/Prop_PUSH.gif");

    @Override
    public boolean applyOnMoveTrigger(BoardElement trigger, BoardElement receiver, Movement movement,
                                      MovementObserver observer) {
      return true;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }

    @Override
    public int priority() {
      return 0;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record Stop() implements MovementProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/STOP/Prop_STOP.gif");

    @Override
    public boolean applyOnMoveTrigger(BoardElement trigger, BoardElement receiver, Movement movement,
                                      MovementObserver observer) {
      return true;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }

    @Override
    public int priority() {
      return 1;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }
}
