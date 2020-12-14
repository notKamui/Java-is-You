package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Displayable;
import com.notkamui.javaisyou.engine.boardelement.*;
import com.notkamui.javaisyou.engine.data.BehaviorData;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.RightOperand;

public sealed interface BoardElement extends Moveable, Stateable, HasFlag, HasProperty, Displayable, HasDirection
        permits Entity, Word {

  void setData(BehaviorData data);

  default LeftOperand getAsLeft() {
    return LeftOperand.nullLeftOperand();
  }

  default RightOperand getAsRight() {
    return RightOperand.nullRightOperand();
  }

  default Operator getAsOperator() {
    return Operator.nullOperator();
  }
}
