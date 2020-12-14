package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.boardelement.Displayable;
import com.notkamui.javaisyou.engine.boardelement.*;
import com.notkamui.javaisyou.engine.data.BehaviorData;
import com.notkamui.javaisyou.engine.data.HasData;

public sealed interface BoardElement extends Moveable, Stateable, HasData, Displayable, HasDirection
        permits Entity, Word {
  void setData(BehaviorData data);
}
