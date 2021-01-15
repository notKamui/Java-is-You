package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.boardelement.HasImage;

import javax.swing.*;

/**
 * Represents a rule part
 */
public interface RulePart extends HasImage {
  /**
   * Default/Null rule part
   */
  RulePart NULL_RULE_PART = new RulePart() {
    @Override
    public ImageIcon image() {
      return null;
    }

    @Override
    public LeftOperand getAsLeftOperand() {
      return LeftOperand.NULL_LEFT_OPERAND;
    }

    @Override
    public Operator getAsOperator() {
      return Operator.NULL_OPERATOR;
    }

    @Override
    public RightOperand getAsRightOperand() {
      return RightOperand.NULL_RIGHT_OPERAND;
    }
  };

  /**
   * Getter on itself as a left operand
   *
   * @return itself as a left operand
   */
  LeftOperand getAsLeftOperand();

  /**
   * Getter on itself as an operator
   *
   * @return itself as an operator
   */
  Operator getAsOperator();

  /**
   * Getter on itself as a right operand
   *
   * @return itself as a right operand
   */
  RightOperand getAsRightOperand();

}
