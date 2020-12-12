package com.notkamui.javaisyou.engine.boardelement;

import java.awt.image.BufferedImage;

public sealed interface BoardElement extends Moveable, Stateable, HasFlag, HasProperty, HasImage
        permits Entity, Noun, Operator, TextualProperty {

}
