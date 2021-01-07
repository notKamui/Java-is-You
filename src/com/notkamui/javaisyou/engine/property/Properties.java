package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;

class Properties {
   static void melting(BoardElement first, BoardElement second, PropertyChecker checker) {
       if (checker.hasProperty(OperandType.MELT, first.id()) && checker.hasProperty(OperandType.HOT, second.id())) {
           first.setState(false);
       } else if (checker.hasProperty(OperandType.MELT, second.id()) &&
               checker.hasProperty(OperandType.HOT, first.id())){
           second.setState(false);
       }
   }
}
