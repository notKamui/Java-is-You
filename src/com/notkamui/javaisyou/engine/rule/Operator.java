package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.manager.TypeModifier;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;

public interface Operator extends RulePart {
  Operator NULL_OPERATOR = new Operator() {
    @Override
    public ImageIcon image() {
      return null;
    }

    @Override
    public boolean acceptAsRight(Type rightOperand) {
      return false;
    }

    @Override
    public boolean acceptAsRight(Property rightOperand) {
      return false;
    }

    @Override
    public Operator getAsOperator() {
      return this;
    }
  };

  @Override
  default LeftOperand getAsLeftOperand() {
    return LeftOperand.NULL_LEFT_OPERAND;
  }

  @Override
  default RightOperand getAsRightOperand() {
    return RightOperand.NULL_RIGHT_OPERAND;
  }

  default boolean onMove(RightOperand rightOperand, BoardElement trigger, BoardElement receiver,
                         PropertyChecker checker, Movement movement, MovementObserver observer) {
    return true;
  }

  default void onSuperposition(RightOperand rightOperand, BoardElement first, BoardElement second,
                               PropertyChecker checker) {}

  default void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, TypeModifier modifier) {}

  boolean acceptAsRight(Type rightOperand);

  boolean acceptAsRight(Property rightOperand);
}
