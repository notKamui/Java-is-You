package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.OperationResult;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public sealed interface MovementProperty extends Property {
  boolean applyOnMoveTrigger(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer);

  boolean applyOnMoveReceiver(BoardElement trigger, BoardElement receiver, Movement movement,
                              MovementObserver observer);

  record Push() implements MovementProperty {
    private final static PropertyType type = PropertyType.PUSH;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/PUSH/Prop_PUSH.gif");


    @Override
    public boolean applyOnMoveTrigger(BoardElement trigger, BoardElement receiver, Movement movement,
                                      MovementObserver observer) {
      return true;
    }

    @Override
    public boolean applyOnMoveReceiver(BoardElement trigger, BoardElement receiver, Movement movement,
                                       MovementObserver observer) {
      Objects.requireNonNull(trigger);
      Objects.requireNonNull(receiver);
      Objects.requireNonNull(movement);
      Objects.requireNonNull(observer);
      return observer.tryToMove(receiver, movement);
    }

    @Override
    public int priority() {
      return 0;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record Stop() implements MovementProperty {
    private final static PropertyType type = PropertyType.STOP;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/STOP/Prop_STOP.gif");

    @Override
    public boolean applyOnMoveTrigger(BoardElement trigger, BoardElement receiver, Movement movement,
                                      MovementObserver observer) {
      return true;
    }

    @Override
    public boolean applyOnMoveReceiver(BoardElement trigger, BoardElement receiver, Movement movement,
                                       MovementObserver observer) {
      return false;
    }

    @Override
    public int priority() {
      return 1;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }
}
