package com.notkamui.javaisyou.engine.boardelement.word.operator;

import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import java.util.Objects;

public final class IsOperator implements Operator {

    private WordWrapper wordWrapper;

    public IsOperator(WordWrapper wordWrapper) {
        Objects.requireNonNull(wordWrapper);
        this.wordWrapper = wordWrapper;
    }

    @Override
    public void apply(EntityWrapper leftType, EntityWrapper rightType) {
        rightType.setData(leftType.getData());
    }

    @Override
    public void apply(EntityWrapper type, Property property) {
        type.addProperty(property);
    }
}
