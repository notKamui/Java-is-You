package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.property.OperandType;

import java.util.Objects;

public record Rule(Type type, Operator operator, RightOperand rightOperand) {
    public Rule {
        Objects.requireNonNull(type);
        Objects.requireNonNull(operator);
        Objects.requireNonNull(rightOperand);
    }

    public OperandType rightOperandType() {
        return rightOperand.operandType();
    }

    public boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                   Movement movement, MovementObserver observer) {
        Objects.requireNonNull(trigger);
        Objects.requireNonNull(receiver);
        Objects.requireNonNull(movement);
        Objects.requireNonNull(observer);
        return operator.onMove(trigger, receiver, checker, movement, observer);
    }

    public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        operator.onSuperposition(first, second, checker);
    }

    public void onRuleCreation() {
        operator.onRuleCreation();
    }
}
