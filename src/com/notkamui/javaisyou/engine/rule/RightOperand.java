package com.notkamui.javaisyou.engine.rule;


import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.manager.TypeModifier;

import javax.swing.*;

public interface RightOperand extends RulePart {
  RightOperand NULL_RIGHT_OPERAND = new RightOperand() {
    @Override
    public ImageIcon image() {
      return null;
    }

    @Override
    public RightOperandType operandType() {
      return RightOperandType.NULL_OPERAND;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      return false;
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }
  };

  @Override
  default LeftOperand getAsLeftOperand() {
    return  LeftOperand.NULL_LEFT_OPERAND;
  }

  @Override
  default Operator getAsOperator() {
    return Operator.NULL_OPERATOR;
  }

  RightOperandType operandType();

  default boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                         Movement movement, MovementObserver observer) {
    return true;
  }

  default void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {}

  default void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, TypeModifier modifier) {}

  boolean acceptedAsRight(Operator operator);
}
