package com.notkamui.javaisyou.engine.boardelement.word.operator;

import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.type.BasicBabaType;
import com.notkamui.javaisyou.engine.type.BabaWordType;

import java.util.Objects;

public final class IsOperator implements Operator {

    private BabaWordType babaWordType;

    public IsOperator(BabaWordType babaWordType) {
        Objects.requireNonNull(babaWordType);
        this.babaWordType = babaWordType;
    }

    @Override
    public void apply(BasicBabaType leftType, BasicBabaType rightType) {
        rightType.setData(leftType);
    }

    @Override
    public void apply(BasicBabaType type, Property property) {
        type.addProperty(property);
    }
}
