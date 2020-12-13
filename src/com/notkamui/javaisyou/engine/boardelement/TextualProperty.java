package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public final class TextualProperty implements BoardElement {
    private final WordWrapper wordWrapper;
    private final BoardElementComponent component;
    private final Property property;

    public TextualProperty(WordWrapper wordWrapper, Direction dir, int x, int y, Property property) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(dir);
        this.wordWrapper = wordWrapper;
        this.property = property;
        this.component = new BoardElementComponent(dir, x, y);
    }

    public Property property() {
        return property;
    }

    @Override
    public Set<PropertyFlag> flags() {
        return wordWrapper.flags();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return wordWrapper.movementProperties();
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
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
}
