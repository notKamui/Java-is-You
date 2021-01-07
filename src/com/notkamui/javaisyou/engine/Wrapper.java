package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.property.OperandType;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.rule.Type;

public record Wrapper(long id) implements Type, RightOperand, RulePart {
    public Wrapper {
        if (id < 0) {
            throw new IllegalArgumentException("id < 0");
        }
    }

    @Override
    public Type getAsType() {
        return this;
    }

    @Override
    public RightOperand getAsRightOperand() {
        return this;
    }

    @Override
    public OperandType operandType() {
        return OperandType.TYPE;
    }
}
