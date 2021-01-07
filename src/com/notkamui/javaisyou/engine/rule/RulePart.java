package com.notkamui.javaisyou.engine.rule;

public interface RulePart {
    RulePart nullRulePart = new RulePart() {
    };

    default Type getAsType() {
        return Type.NULL_TYPE;
    }

    default Operator getAsOperator() {
        return Operator.NULL_OPERATOR;
    }

    default RightOperand getAsRightOperand() {
        return RightOperand.NULL_RIGHT_OPERAND;
    }
}
