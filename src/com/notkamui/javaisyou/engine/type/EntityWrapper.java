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

public final class EntityWrapper implements Wrapper {
    private EntityData data;

    public EntityWrapper(ImageIcon elemImg , ImageIcon nounImg) {
        Objects.requireNonNull(elemImg);
        Objects.requireNonNull(nounImg);
        data = new EntityData(elemImg, nounImg);
    }

    public void setWrapper(EntityWrapper wrapper) {
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
        Objects.requireNonNull(prop);
        data.removeProperty(prop);
    }

    @Override
    public Set<PropertyFlag> flags() {
        return data.flags();
    }

    @Override
    public String toString() {
        return data.toString();
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
        return leftOperand.applyIsAsLeft(this);
    }


    @Override
    public Result applyIsAsLeft(WordWrapper rightOperand) {
        return Result.ENTITY_TO_TEXT;
    }

    @Override
    public Result applyIsAsLeft(EntityWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        return Result.NORMAL;
    }

    @Override
    public Result applyIsAsLeft(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
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
