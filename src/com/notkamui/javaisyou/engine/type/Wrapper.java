package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.Property;

public sealed interface Wrapper extends HasFlag, HasProperty, HasEntityImage, RightOperand, LeftOperand
        permits EntityWrapper, WordWrapper {

    void addProperty(Property prop);

    void removeProperty(Property prop);

}
