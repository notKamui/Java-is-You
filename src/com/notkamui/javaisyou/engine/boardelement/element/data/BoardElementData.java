package com.notkamui.javaisyou.engine.boardelement.element.data;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasImage;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.boardelement.OperationElement;

public sealed interface BoardElementData extends HasFlag, HasProperty, HasImage, OperationElement
        permits Entity, Noun, TextualOperator, TextualProperty {
}
