package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;

public sealed interface WrapperData extends HasFlag, HasEntityImage, EditableProperties
        permits WordData, EntityData {

}
