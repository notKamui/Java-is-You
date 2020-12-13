package com.notkamui.javaisyou.engine.boardelement.element.data;

import com.notkamui.javaisyou.engine.boardelement.element.data.BoardElementData;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.EntityAspect;
import com.notkamui.javaisyou.engine.type.Wrapper;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public final class Entity implements BoardElementData {
    private final Wrapper wrapper;
    public Entity(Wrapper wrapper) {
        Objects.requireNonNull(wrapper);
        this.wrapper = wrapper;
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
        return wrapper.entityIcon(EntityAspect.ELEMENT);
    }

}
