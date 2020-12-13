package com.notkamui.javaisyou.engine.boardelement.element.data;

import com.notkamui.javaisyou.engine.boardelement.element.data.BoardElementData;
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

public final class Noun implements BoardElementData {
    private final Wrapper nounWrapper;
    private final Wrapper representedWrapper;

    public Noun (WordWrapper nounWrapper, Wrapper representedWrapper) {
        Objects.requireNonNull(nounWrapper);
        Objects.requireNonNull(representedWrapper);
        this.nounWrapper = nounWrapper;
        this.representedWrapper = representedWrapper;
    }

    @Override
    public Set<PropertyFlag> flags() {
        return nounWrapper.flags();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return nounWrapper.movementProperties();
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return nounWrapper.passiveProperties();
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
