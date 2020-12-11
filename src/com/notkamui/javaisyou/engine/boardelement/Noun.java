package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.WordWrapper;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.util.Objects;
import java.util.Set;

public final class Noun implements BoardElement, Applicable {
    private final WordWrapper wordWrapper;
    private final Wrapper representedWrapper;
    private final GameObjectComponent component;

    public Noun (WordWrapper wordWrapper, Direction dir, int x, int y, Wrapper representedWrapper) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(dir);
        Objects.requireNonNull(representedWrapper);
        this.wordWrapper = wordWrapper;
        this.representedWrapper = representedWrapper;
        this.component = new GameObjectComponent(dir, x, y);
    }

    public Wrapper representedWrapper() {
        return representedWrapper;
    }

    @Override
    public boolean hasFlag(PropertyFlag propertyFlag) {
        return wordWrapper.hasFlag(propertyFlag);
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
        if (!(o instanceof Noun that)) return false;
        return  component.x() == that.x() && component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }


}
