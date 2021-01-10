package com.notkamui.javaisyou.engine.rule.rulepart;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.manager.TypeModifier;
import com.notkamui.javaisyou.engine.rule.LeftOperand;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;
import java.util.Objects;

public record IsOperator() implements Operator {
  private final static ImageIcon icon = new ImageIcon("resources/assets/operators/IS/Op_IS.gif");

  @Override
  public boolean onMove(RightOperand rightOperand, BoardElement trigger, BoardElement receiver,
                        PropertyChecker checker, Movement movement, MovementObserver observer) {
    return true;
  }

  @Override
  public void onSuperposition(RightOperand rightOperand, BoardElement first, BoardElement second,
                              PropertyChecker checker) {

  }

  @Override
  public void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, TypeModifier modifier) {
    Objects.requireNonNull(rightOperand);
    rightOperand.onRuleCreation(leftOperand, rightOperand, modifier);
  }

  @Override
  public boolean acceptAsRight(Type rightOperand) {
    Objects.requireNonNull(rightOperand);
    return true;
  }

  @Override
  public boolean acceptAsRight(Property rightOperand) {
    Objects.requireNonNull(rightOperand);
    return true;
  }

  @Override
  public Operator getAsOperator() {
    return this;
  }

  @Override
  public ImageIcon image() {
    return icon;
  }
}