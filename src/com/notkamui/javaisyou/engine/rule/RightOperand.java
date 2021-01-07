package com.notkamui.javaisyou.engine.rule;


import com.notkamui.javaisyou.engine.property.OperandType;

public non-sealed interface RightOperand extends OperationPart {
    RightOperand NULL_RIGHT_OPERAND = () -> OperandType.NULL_OPERAND;

    OperandType operandType();
}
