package com.notkamui.javaisyou.engine.boardelement.element;

public sealed interface Word extends BoardElement permits Noun, TextualOperator, TextualProperty{
}
