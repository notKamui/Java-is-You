package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.data.BehaviorData;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.Wrapper;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Noun implements Word {
    private BehaviorData data = BehaviorData.emptyData();
    private final Wrapper representedWrapper;
    private final BoardElementComponent component;

    public Noun (Direction dir, int x, int y, Wrapper representedWrapper) {
        Objects.requireNonNull(dir);
        Objects.requireNonNull(representedWrapper);
        this.representedWrapper = representedWrapper;
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
        if (!(o instanceof Noun that)) return false;
        return  component.x() == that.x() && component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }

    @Override
    public ImageIcon image() {
        return representedWrapper.image();
    }

    @Override
    public LeftOperand getAsLeft() {
        return representedWrapper;
    }

    @Override
    public RightOperand getAsRight() {
        return representedWrapper;
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
