package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.property.Property;
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
    public void apply(Noun noun, Applicable applicable) {
        if (applicable instanceof Noun) {
            var leftWrapper = noun.representedWrapper();
            var rightWrapper = ((Noun) applicable).representedWrapper();
            if (leftWrapper instanceof WordWrapper || rightWrapper instanceof WordWrapper) {
                // TODO to implement
            } else {
                ((EntityWrapper) leftWrapper).setData(((EntityWrapper) rightWrapper).getData());
            }
        } else { // is Property
            noun.representedWrapper().addProperty((Property) applicable);
        }
    }

    @Override
    public void unapply(Noun noun, Applicable applicable) {
        if (applicable instanceof Property) {
            noun.representedWrapper().removeProperty((Property) applicable);
        }
    }
}
