package com.notkamui.javaisyou.engine.rule.rulepart.property;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

class Properties {
   static void melting(BoardElement first, BoardElement second, PropertyChecker checker) {
       if (checker.hasProperty(RightOperandType.MELT, first.id()) && checker.hasProperty(RightOperandType.HOT, second.id())) {
           first.setState(false);
       } else if (checker.hasProperty(RightOperandType.MELT, second.id()) &&
               checker.hasProperty(RightOperandType.HOT, first.id())){
           second.setState(false);
       }
   }
}
