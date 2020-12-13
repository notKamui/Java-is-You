package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.RightOperand;

public sealed interface BoardElement extends Moveable, Stateable, HasFlag, HasProperty, HasImage
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
