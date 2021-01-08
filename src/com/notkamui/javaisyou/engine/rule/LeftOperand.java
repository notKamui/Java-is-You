package com.notkamui.javaisyou.engine.rule;

import javax.swing.*;

public interface LeftOperand extends RulePart {
    LeftOperand NULL_LEFT_OPERAND = new LeftOperand() {
        @Override
        public ImageIcon image() {
            return null;
        }

        @Override
        public long id() {
            return -1;
        }

        @Override
        public LeftOperand getAsLeftOperand() {
            return this;
        }
    };

    @Override
    default Operator getAsOperator() {
        return Operator.NULL_OPERATOR;
    }

    @Override
    default RightOperand getAsRightOperand() {
        return RightOperand.NULL_RIGHT_OPERAND;
    }

    long id();
}
