package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Result;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public final class WordWrapper implements Wrapper, LeftOperand {
    private final WrapperData initialData;
    private WrapperData data;

    public WordWrapper(ImageIcon nounIcon) {
        Objects.requireNonNull(nounIcon);
        this.data = this.initialData = new WordData(nounIcon);
    }

    public void setWrapper(WordWrapper wrapper) {
        Objects.requireNonNull(wrapper);
        this.data = wrapper.data;
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return data.passiveProperties();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return data.movementProperties();
    }

    @Override
    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        data.addProperty(prop);
    }

    @Override
    public void removeProperty(Property prop) {
        data.removeProperty(prop);
    }

    @Override
    public Set<PropertyFlag> flags() {
        return data.flags();
    }

    @Override
    public ImageIcon entityIcon(EntityAspect aspect) {
        Objects.requireNonNull(aspect);
        return data.entityIcon(aspect);
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
        Objects.requireNonNull(leftOperand);
        return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
        Objects.requireNonNull(leftOperand);
        return leftOperand.unapplyIsAsLeft(this);
    }

    @Override
    public Result applyIsAsLeft(WordWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        this.data = rightOperand.data;
        return Result.NORMAL;
        // TODO normal or ineffective :thonk:
    }

    @Override
    public Result applyIsAsLeft(EntityWrapper rightOperand) {
        return Result.TEXT_TO_ENTITY;
    }

    @Override
    public Result applyIsAsLeft(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.addProperty(rightOperand);
        return Result.NORMAL;
    }

    @Override
    public Result unapplyIsAsLeft(WordWrapper rightOperand) {
        return Result.INEFFECTIVE;
    }

    @Override
    public Result unapplyIsAsLeft(EntityWrapper rightOperand) {
        return Result.INEFFECTIVE;
    }

    @Override
    public Result unapplyIsAsLeft(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.removeProperty(rightOperand);
        return Result.NORMAL;
    }
}
