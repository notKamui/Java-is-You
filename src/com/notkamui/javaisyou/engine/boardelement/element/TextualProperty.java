package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.data.BehaviorData;
import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

public final class TextualProperty implements Word {
    private BehaviorData data = BehaviorData.emptyData();
    private final BoardElementComponent component;
    private final Property property;

    public TextualProperty(Direction dir, int x, int y, Property property) {
        Objects.requireNonNull(dir);
        this.property = property;
        this.component = new BoardElementComponent(dir, x, y);
    }

    public Property property() {
        return property;
    }

    @Override
    public Set<PropertyFlag> flags() {
        return data.flags();
    }

    @Override
    public SortedSet<MovementProperty> movementProperties() {
        return data.movementProperties();
    }

    @Override
    public SortedSet<PassiveProperty> passiveProperties() {
        return data.passiveProperties();
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
        if (!(o instanceof TextualProperty that)) return false;
        return component.x() == that.x() && component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }

    @Override
    public ImageIcon image() {
        return property.image();
    }

    @Override
    public RightOperand getAsRight() {
        return property;
    }

    @Override
    public Direction direction() {
        return component.direction();
    }

    @Override
    public void setData(BehaviorData data) {
        Objects.requireNonNull(data);
        this.data = data;
    }

}
