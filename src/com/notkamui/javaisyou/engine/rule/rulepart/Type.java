package com.notkamui.javaisyou.engine.rule.rulepart;

import com.notkamui.javaisyou.engine.manager.TypeModifier;
import com.notkamui.javaisyou.engine.rule.LeftOperand;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

import javax.swing.*;
import java.util.Objects;

public record Type(ImageIcon image, ImageIcon elemImage) implements LeftOperand, RightOperand {
  public Type {
    Objects.requireNonNull(image);
  }

  @Override
  public LeftOperand getAsLeftOperand() {
    return this;
  }

  @Override
  public Operator getAsOperator() {
    return Operator.NULL_OPERATOR;
  }

  @Override
  public RightOperand getAsRightOperand() {
    return this;
  }

  @Override
  public RightOperandType operandType() {
    return RightOperandType.TYPE;
  }

  @Override
  public boolean acceptedAsRight(Operator operator) {
    Objects.requireNonNull(operator);
    return operator.acceptAsRight(this);
  }

  @Override
  public void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, TypeModifier modifier) {
    Objects.requireNonNull(leftOperand);
    Objects.requireNonNull(rightOperand);
    Objects.requireNonNull(modifier);
    modifier.modify(leftOperand, this);
  }

  @Override
  public Type getAsType() {
    return this;
  }
}
