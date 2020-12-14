package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.OperationResult;
import com.notkamui.javaisyou.engine.operation.RightOperand;

import java.util.Objects;

public record Rule(LeftOperand leftOperand, Operator Operator, RightOperand rightOperand) {
    public Rule {
        Objects.requireNonNull(leftOperand);
        Objects.requireNonNull(Operator);
        Objects.requireNonNull(rightOperand);
    }

    public OperationResult apply() {
        return Operator.apply(leftOperand, rightOperand);
    }

    public OperationResult unapply() {
        return Operator.unapply(leftOperand, rightOperand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return leftOperand.equals(rule.leftOperand) &&
                Operator.equals(rule.Operator) &&
                rightOperand.equals(rule.rightOperand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, Operator, rightOperand);
    }
}
