package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.EntityAspect;
import com.notkamui.javaisyou.engine.type.EntityWrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

public final class Entity implements BoardElement {
    private final EntityWrapper entityWrapper;
    private final BoardElementComponent component;


    public Entity(EntityWrapper entityWrapper, Direction dir, int x, int y) {
        Objects.requireNonNull(entityWrapper);
        Objects.requireNonNull(dir);
        this.entityWrapper = entityWrapper;
        this.component = new BoardElementComponent(dir, x, y);
    }

    @Override
    public Set<PropertyFlag> flags() {
        return entityWrapper.flags();
    }

    @Override
    public SortedSet<MovementProperty> movementProperties() {
        return entityWrapper.movementProperties();
    }

    @Override
    public SortedSet<PassiveProperty> passiveProperties() {
        return entityWrapper.passiveProperties();
    }

    @Override
    public int x() {
        return component.x();
    }

    @Override
    public int y() {
        return component.y();
    }

    @Override
    public boolean state() {
        return component.state();
    }

    @Override
    public void move(Movement move) {
        component.move(move);
    }

    @Override
    public void setState(boolean state) {
        component.setState(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity that)) return false;
        return entityWrapper.equals(that.entityWrapper) &&
                component.x() == that.x() &&
                component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }

    @Override
    public ImageIcon image() {
        return entityWrapper.entityIcon(EntityAspect.ELEMENT);
    }

    @Override
    public LeftOperand getAsLeft() {
        return entityWrapper;
    }
}
