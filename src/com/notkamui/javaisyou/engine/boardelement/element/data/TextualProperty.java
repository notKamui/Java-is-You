package com.notkamui.javaisyou.engine.boardelement.element.data;

import com.notkamui.javaisyou.engine.boardelement.element.data.BoardElementData;
import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.Wrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public final class TextualProperty implements BoardElementData {
    private final Wrapper wrapper;
    private final Property property;

    public TextualProperty(Wrapper wrapper, Property property) {
        Objects.requireNonNull(wrapper);
        Objects.requireNonNull(property);
        this.wrapper = wrapper;
        this.property = property;
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
        return property.image();
    }

    @Override
    public RightOperand getAsRight() {
        return property;
    }
}
