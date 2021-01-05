package com.notkamui.javaisyou.engine;

import java.util.Objects;

public record Rule(LeftOperand leftOperand, Operator Operator, RightOperand rightOperand) {
    public Rule {
        Objects.requireNonNull(leftOperand);
        Objects.requireNonNull(Operator);
        Objects.requireNonNull(rightOperand);
    }

    boolean onMove(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer) {
        Objects.requireNonNull(trigger);
        Objects.requireNonNull(receiver);
        Objects.requireNonNull(movement);
        Objects.requireNonNull(observer);
        return operator.onMove(trigger, receiver, movement, observer);
    }

    void onSuperposition(BoardElement first, BoardElement second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        operator.onSuperposition(first, second);
    }

    void onRuleCreation() {
        operator.onRuleCreation();
    }
}
