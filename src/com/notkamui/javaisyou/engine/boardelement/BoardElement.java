package com.notkamui.javaisyou.engine.boardelement;

public sealed interface BoardElement extends Moveable, Stateable, HasFlag, HasProperty
        permits Entity, Noun, Operator, TextualProperty {

}
