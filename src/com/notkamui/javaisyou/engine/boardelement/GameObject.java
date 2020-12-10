package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.util.List;

public sealed interface GameObject extends Moveable, Stateable, Flaggable
        permits Entity, Noun, Operator, TextualProperty {

    Wrapper wrapper();

    List<Property> properties();
}
