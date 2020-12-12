package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import java.util.Set;

public sealed interface Wrapper extends HasFlag, HasProperty
        permits EntityWrapper, WordWrapper {

    void addProperty(Property prop);

    void removeProperty(Property prop);

}
