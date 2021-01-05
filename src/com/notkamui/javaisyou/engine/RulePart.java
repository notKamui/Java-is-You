package com.notkamui.javaisyou.engine;

public interface RulePart {
    RulePart nullRulePart = new RulePart() {
    };

    default Type getAsType() {
        return Type.nullType;
    }

    default Operator getAsOperator() {
        return Operator.nullOperator;
    }

    default RightOperand getAsRightOperand() {
        return RightOperand.nullRO;
    }
}
