package com.notkamui.javaisyou.engine.rule.rulepart;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementEditor;
import com.notkamui.javaisyou.engine.rule.*;

import javax.swing.*;
import java.util.Objects;

/**
 * This record represents the type of an element (in both its text and item version)
 * A Type knows both its images
 */
public record Type(ImageIcon image, ImageIcon elemImage) implements LeftOperand, RightOperand {
    /**
     * Constructor for a Type
     *
     * @param image     the text image
     * @param elemImage the element image
     */
    public Type {
        Objects.requireNonNull(image);
        Objects.requireNonNull(elemImage);
    }

    @Override
    public LeftOperand getAsLeftOperand() {
        return this;
    }

    @Override
    public Operator getAsOperator() {
        return Operator.NULL_OPERATOR;
    }

    @Override
    public RightOperand getAsRightOperand() {
        return this;
    }

    @Override
    public RightOperandType operandType() {
        return RightOperandType.TYPE;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
        Objects.requireNonNull(operator);
        return operator.acceptAsRight(this);
    }

    @Override
    public void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, ElementEditor elementEditor) {
        Objects.requireNonNull(leftOperand);
        Objects.requireNonNull(rightOperand);
        Objects.requireNonNull(elementEditor);

        var oldType = leftOperand.getAsType();
        if (oldType == this) {
            return;
        }
        var toModify = elementEditor.elementsFiltered(e -> oldType.equals(e.type()));
        toModify.forEach(element -> {
            element.setType(this);
//      if (element.rulePart() != RulePart.NULL_RULE_PART) { // text -> entity
//        element.setRulePart(RulePart.NULL_RULE_PART);
//      }
            // TODO entity -> text
        });
    }

    @Override
    public Type getAsType() {
        return this;
    }

    @Override
    public void onDeath(BoardElement dyingElement, ElementEditor elementEditor) {
        Objects.requireNonNull(dyingElement);
        Objects.requireNonNull(elementEditor);
        var clone = dyingElement.clone();
        Objects.requireNonNull(clone);
        clone.setType(this);
        clone.setState(true);
        elementEditor.addElement(clone);
    }
}
