package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.HasImage;

public sealed interface Property extends HasImage, RightOperand
        permits MovementProperty, PassiveProperty {
    int priority();

    PropertyType type();
}
