package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasImage;

public sealed interface Property extends HasFlag, HasImage, RightOperand
        permits MovementProperty, PassiveProperty {
    int priority();
}
