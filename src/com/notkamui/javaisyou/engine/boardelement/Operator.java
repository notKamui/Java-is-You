package com.notkamui.javaisyou.engine.boardelement;

public non-sealed interface Operator extends BoardElement {
    void apply(Noun noun, Applicable applicable);

    void unapply(Noun noun, Applicable applicable);
}
