package com.notkamui.javaisyou.engine.rule;


import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementEditor;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;

import javax.swing.*;

/**
 * Represents rule parts that can be right operands
 */
public interface RightOperand extends RulePart {
    /**
     * Default/Null right operand
     */
    RightOperand NULL_RIGHT_OPERAND = new RightOperand() {
        @Override
        public ImageIcon image() {
            return null;
        }

        @Override
        public RightOperandType operandType() {
            return RightOperandType.NULL_OPERAND;
        }

        @Override
        public boolean acceptedAsRight(Operator operator) {
            return false;
        }

        @Override
        public RightOperand getAsRightOperand() {
            return this;
        }
    };

    @Override
    default LeftOperand getAsLeftOperand() {
        return LeftOperand.NULL_LEFT_OPERAND;
    }

    @Override
    default Operator getAsOperator() {
        return Operator.NULL_OPERATOR;
    }

    /**
     * Getter on its operand type
     *
     * @return the operand type
     */
    RightOperandType operandType();

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
    default boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                           Movement movement, MovementObserver observer) {
        return true;
    }

    /**
     * Is applied when the first element is superimposed with the second one
     *
     * @param first   the first element
     * @param second  the second element
     * @param checker the property checker
     */
    default void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
        // DO NOTHING
    }

    /**
     * Is applied when the dyingElement dies
     *
     * @param dyingElement  the element that is dying
     * @param elementEditor the element editor
     */
    default void onDeath(BoardElement dyingElement, ElementEditor elementEditor) {
        // DO NOTHING
    }

    /**
     * Is applied when the rule is created
     *
     * @param leftOperand   the left operand of the rule
     * @param rightOperand  the right operand of the rule
     * @param elementEditor the element editor
     */
    default void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, ElementEditor elementEditor) {
        // DO NOTHING
    }

    /**
     * Checks if an Operator is accepted as a right operand
     *
     * @param operator the supposed right operand
     * @return true if accepted, false otherwise
     */
    boolean acceptedAsRight(Operator operator);
}
