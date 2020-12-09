package com.notkamui.javaisyou.engine.boardelement.word.operator;

import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.type.BasicBabaType;

public sealed interface Operator permits IsOperator {

    void apply(BasicBabaType leftType, BasicBabaType rightType);

    void apply(BasicBabaType type, Property property);

}
