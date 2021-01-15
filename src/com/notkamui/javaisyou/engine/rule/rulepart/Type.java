package com.notkamui.javaisyou.engine.rule.rulepart;

import com.notkamui.javaisyou.engine.manager.ElementProvider;
import com.notkamui.javaisyou.engine.rule.LeftOperand;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

import javax.swing.*;
import java.util.Objects;

public record Type(ImageIcon image, ImageIcon elemImage) implements LeftOperand, RightOperand {
  public Type {
    Objects.requireNonNull(image);
    Objects.requireNonNull(elemImage);
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
  public void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, ElementProvider provider) {
    Objects.requireNonNull(leftOperand);
    Objects.requireNonNull(rightOperand);
    Objects.requireNonNull(provider);

    var oldType = leftOperand.getAsType();
    if (oldType == this) {
      return;
    }
    var toModify = provider.elementsFiltered(e -> oldType.equals(e.type()));
    toModify.forEach(element -> {
      element.setType(this);
//      if (element.rulePart() != RulePart.NULL_RULE_PART) { // text -> entity
//        element.setRulePart(RulePart.NULL_RULE_PART);
//      }
      // TODO entity -> text
    });
  }

  @Override
  public Type getAsType() {
    return this;
  }
}
