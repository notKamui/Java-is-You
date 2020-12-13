package com.notkamui.javaisyou.engine.boardelement.element.data;

import com.notkamui.javaisyou.engine.babaoperator.BabaOperator;
import com.notkamui.javaisyou.engine.boardelement.element.data.BoardElementData;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.Wrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public final class TextualOperator implements BoardElementData {
    private final Wrapper wrapper;
    private final BabaOperator babaOperator;

    public TextualOperator(Wrapper wrapper, BabaOperator babaOperator) {
        Objects.requireNonNull(wrapper);
        Objects.requireNonNull(babaOperator);
        this.wrapper = wrapper;
        this.babaOperator = babaOperator;
    }

    @Override
    public Set<PropertyFlag> flags() {
        return wrapper.flags();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return wrapper.movementProperties();
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return wrapper.passiveProperties();
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
