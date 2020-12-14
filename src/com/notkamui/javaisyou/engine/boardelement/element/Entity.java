package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.data.BehaviorData;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Entity implements BoardElement {
    private BehaviorData data = BehaviorData.emptyData();
    private final BoardElementComponent component;

    public Entity(Direction dir, int x, int y) {
        Objects.requireNonNull(dir);
        this.component = new BoardElementComponent(dir, x, y);
    }

    @Override
    public Set<PropertyFlag> flags() {
        return data.flags();
    }

    @Override
    public List<MovementProperty> movementProperties() {
        return data.movementProperties();
    }

    @Override
    public List<PassiveProperty> passiveProperties() {
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
        if (!(o instanceof Entity that)) return false;
        return data.equals(that.data) &&
                component.x() == that.x() &&
                component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }

    @Override
    public ImageIcon image() {
        return data.image();
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
