package com.notkamui.javaisyou.engine.rule.rulepart;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementEditor;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;
import java.util.Objects;

/**
 * The HAS operator
 * When an element of the left type disappear, it is replaced by an element of the right type
 */
public record HasOperator() implements Operator {
    private final static ImageIcon icon = new ImageIcon("resources/assets/operators/HAS/Op_HAS.gif");

    @Override
    public boolean onMove(RightOperand rightOperand, BoardElement trigger, BoardElement receiver,
                          PropertyChecker checker, Movement movement, MovementObserver observer) {
        Objects.requireNonNull(rightOperand);
        Objects.requireNonNull(trigger);
        Objects.requireNonNull(receiver);
        Objects.requireNonNull(movement);
        Objects.requireNonNull(observer);
        return true;
    }

    @Override
    public boolean acceptAsRight(Type rightOperand) {
        Objects.requireNonNull(rightOperand);
        return true;
    }

    @Override
    public boolean acceptAsRight(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
        return false;
    }

    @Override
    public Operator getAsOperator() {
        return this;
    }

    @Override
    public void onDeath(BoardElement dyingElement, RightOperand rightOperand, ElementEditor elementEditor) {
        Objects.requireNonNull(dyingElement);
        Objects.requireNonNull(rightOperand);
        rightOperand.onDeath(dyingElement, elementEditor);
    }

    @Override
    public ImageIcon image() {
        return icon;
    }
}
