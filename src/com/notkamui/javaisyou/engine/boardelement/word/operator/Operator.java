package com.notkamui.javaisyou.engine.boardelement.word.operator;

import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.type.EntityWrapper;

public sealed interface Operator permits IsOperator {

    void apply(EntityWrapper leftType, EntityWrapper rightType);

    void apply(EntityWrapper type, Property property);

}
