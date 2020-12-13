package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.babaoperator.BabaOperator;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public final class TextualOperator implements BoardElement {
    private final WordWrapper wordWrapper;
    private final BoardElementComponent component;
    private final BabaOperator babaOperator;

    public TextualOperator(WordWrapper wordWrapper, Direction dir, int x, int y, BabaOperator babaOperator) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(dir);
        Objects.requireNonNull(babaOperator);
        this.wordWrapper = wordWrapper;
        this.component = new BoardElementComponent(dir, x, y);
        this.babaOperator = babaOperator;
    }

    public BabaOperator operator() {
        return babaOperator;
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
        if (!(o instanceof TextualOperator that)) return false;
        return  component.x() == that.x() && component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }

    @Override
    public ImageIcon image() {
        return babaOperator.image();
    }

    @Override
    public Operator getAsOperator() {
        return babaOperator;
    }
}
