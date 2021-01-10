package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.rule.rulepart.Type;

import javax.swing.*;

public interface LeftOperand extends RulePart {
    LeftOperand NULL_LEFT_OPERAND = new LeftOperand() {
        @Override
        public ImageIcon image() {
            return null;
        }

        @Override
        public LeftOperand getAsLeftOperand() {
            return this;
        }

        @Override
        public Type getAsType() {
            return null;
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

    Type getAsType();
}
