package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementEditor;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;

/**
 * Represents rule parts that can be operators
 */
public interface Operator extends RulePart {
    /**
     * Default/Null operator
     */
    Operator NULL_OPERATOR = new Operator() {
        @Override
        public ImageIcon image() {
            return null;
        }

        @Override
        public boolean acceptAsRight(Type rightOperand) {
            return false;
        }

        @Override
        public boolean acceptAsRight(Property rightOperand) {
            return false;
        }

        @Override
        public Operator getAsOperator() {
            return this;
        }
    };

    @Override
    default LeftOperand getAsLeftOperand() {
        return LeftOperand.NULL_LEFT_OPERAND;
    }

    @Override
    default RightOperand getAsRightOperand() {
        return RightOperand.NULL_RIGHT_OPERAND;
    }

    /**
     * Is applied to when the trigger is moved
     *
     * @param rightOperand the right operand of the rule
     * @param trigger      the trigger of the rule
     * @param receiver     the receiver of the rule
     * @param checker      the property checker
     * @param movement     the movement of the trigger
     * @param observer     the movement observer
     * @return true if the move is successful, false otherwise
     */
    default boolean onMove(RightOperand rightOperand, BoardElement trigger, BoardElement receiver,
                           PropertyChecker checker, Movement movement, MovementObserver observer) {
        return true;
    }

    /**
     * Is applied when the first element is superimposed with the second one
     *
     * @param rightOperand the right operand of the rule
     * @param first        the first element
     * @param second       the second element
     * @param checker      the property checker
     */
    default void onSuperposition(RightOperand rightOperand, BoardElement first, BoardElement second, PropertyChecker checker) {
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
     * Is applied when the dyingElement dies
     *
     * @param dyingElement the element that is dying
     * @param rightOperand the right operand of the rule
     * @param elementEditor the element editor
     */
    default void onDeath(BoardElement dyingElement, RightOperand rightOperand, ElementEditor elementEditor) {
        // DO NOTHING
    }

    /**
     * Checks if a Type is accepted as a right operand
     *
     * @param rightOperand the supposed right operand
     * @return true if accepted, false otherwise
     */
    boolean acceptAsRight(Type rightOperand);

    /**
     * Checks if a Property is accepted as a right operand
     *
     * @param rightOperand the supposed right operand
     * @return true if accepted, false otherwise
     */
    boolean acceptAsRight(Property rightOperand);

    /**
     * Checks if a the rule is a prohibition
     *
     * @param leftOperand the left operand of the rule
     * @param rightOperand the right operand of the rule
     * @return true if is a prohibitions, false otherwise
     */
    default boolean isProhibition(LeftOperand leftOperand, RightOperand rightOperand) {
        return false;
    }

    /**
     * Checks if a right operand can be forbidden for this operator
     *
     * @param rightOperand the right operand of the rule
     * @return true if can be forbidden, false otherwise
     */
    default boolean canBeForbidden(RightOperand rightOperand) {
        return false;
    }
}
