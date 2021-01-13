package com.notkamui.javaisyou.engine.rule.rulepart;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementProvider;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.LeftOperand;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;
import java.util.Objects;

public record HasOperator() implements Operator {
  private final static ImageIcon icon = new ImageIcon("resources/assets/operators/IS/Op_IS.gif");

  @Override
  public boolean onMove(RightOperand rightOperand, BoardElement trigger, BoardElement receiver,
                        PropertyChecker checker, Movement movement, MovementObserver observer) {
    Objects.requireNonNull(rightOperand);
    Objects.requireNonNull(trigger);
    Objects.requireNonNull(receiver);
    Objects.requireNonNull(movement);
    Objects.requireNonNull(observer);
    return true;
  }

  @Override
  public void onSuperposition(RightOperand rightOperand, BoardElement first, BoardElement second,
                              PropertyChecker checker) {
    Objects.requireNonNull(rightOperand);
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);
    Objects.requireNonNull(checker);
    rightOperand.onSuperposition(first, second, checker);
  }

  @Override
  public void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, ElementProvider provider) {
    Objects.requireNonNull(rightOperand);
    rightOperand.onRuleCreation(leftOperand, rightOperand, provider);
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
