package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.HasImage;

import javax.swing.*;

public interface RulePart extends HasImage {
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

    LeftOperand getAsLeftOperand();

    Operator getAsOperator();

    RightOperand getAsRightOperand();
}
