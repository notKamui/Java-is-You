package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Displayable;
import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.boardelement.Moveable;
import com.notkamui.javaisyou.engine.boardelement.Stateable;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.RightOperand;

public sealed interface BoardElement extends Moveable, Stateable, HasFlag, HasProperty, Displayable
        permits Entity, Noun, TextualProperty, TextualOperator {

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
