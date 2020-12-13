package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.EntityAspect;
import com.notkamui.javaisyou.engine.type.WordWrapper;
import com.notkamui.javaisyou.engine.type.Wrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

public final class Noun implements BoardElement {
    private final WordWrapper wordWrapper;
    private final Wrapper representedWrapper;
    private final BoardElementComponent component;

    public Noun (WordWrapper wordWrapper, Direction dir, int x, int y, Wrapper representedWrapper) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(dir);
        Objects.requireNonNull(representedWrapper);
        this.wordWrapper = wordWrapper;
        this.representedWrapper = representedWrapper;
        this.component = new BoardElementComponent(dir, x, y);
    }

    @Override
    public Set<PropertyFlag> flags() {
        return wordWrapper.flags();
    }

    @Override
    public SortedSet<MovementProperty> movementProperties() {
        return wordWrapper.movementProperties();
    }

    @Override
    public SortedSet<PassiveProperty> passiveProperties() {
        return wordWrapper.passiveProperties();
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
        return representedWrapper.entityIcon(EntityAspect.NOUN);
    }

    @Override
    public LeftOperand getAsLeft() {
        return representedWrapper;
    }

    @Override
    public RightOperand getAsRight() {
        return representedWrapper;
    }
}
