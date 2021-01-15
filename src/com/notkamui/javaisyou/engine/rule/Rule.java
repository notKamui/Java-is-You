package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementProvider;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;

import java.util.Objects;

/**
 * This record represents a rule, with a left operand, operator and right operand
 */
public record Rule(LeftOperand leftOperand, Operator operator, RightOperand rightOperand) {
    /**
     * Constructor for a Rule
     *
     * @param leftOperand  the left operand
     * @param operator     the operator
     * @param rightOperand the right operand
     */
    public Rule {
        Objects.requireNonNull(leftOperand);
        Objects.requireNonNull(operator);
        Objects.requireNonNull(rightOperand);
    }

    /**
     * Getter on its right operand type
     *
     * @return the right operand type
     */
    public RightOperandType rightOperandType() {
        return rightOperand.operandType();
    }

    /**
     * Is applied to when the trigger is moved
     *
     * @param trigger  the trigger of the rule
     * @param receiver the receiver of the rule
     * @param checker  the property checker
     * @param movement the movement of the trigger
     * @param observer the movement observer
     * @return true if the move is successful, false otherwise
     */
    public boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                          Movement movement, MovementObserver observer) {
        Objects.requireNonNull(trigger);
        Objects.requireNonNull(receiver);
        Objects.requireNonNull(movement);
        Objects.requireNonNull(observer);
        return operator.onMove(rightOperand, trigger, receiver, checker, movement, observer);
    }

    /**
     * Is applied when the first element is superimposed with the second one
     *
     * @param first   the first element
     * @param second  the second element
     * @param checker the property checker
     */
    public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        Objects.requireNonNull(checker);
        operator.onSuperposition(rightOperand, first, second, checker);
    }

    /**
     * Is applied when the rule is created
     *
     * @param provider the element provider
     */
    public void onRuleCreation(ElementProvider provider) {
        Objects.requireNonNull(provider);
        operator.onRuleCreation(leftOperand, rightOperand, provider);
    }
}
