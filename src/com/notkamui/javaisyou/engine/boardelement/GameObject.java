package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.util.List;
import java.util.Set;

public sealed interface GameObject extends Moveable, Stateable, Flaggable
        permits Entity, Noun, Operator, TextualProperty {

    Wrapper wrapper();

    Set<Property> properties();
}
