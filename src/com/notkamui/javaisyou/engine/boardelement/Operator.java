package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.boardelement.GameObject;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.Wrapper;

public non-sealed interface Operator extends GameObject {
    void apply(Noun noun, Applicable applicable);

    void unapply(Noun noun, Applicable applicable);
}
