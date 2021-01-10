package com.notkamui.javaisyou.engine.rule.rulepart.property;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

class Properties {
   static void melting(BoardElement first, BoardElement second, PropertyChecker checker) {
     if (checker.hasProperty(RightOperandType.MELT, first.type()) && checker.hasProperty(RightOperandType.HOT, second.type())) {
       first.setState(false);
     } else if (checker.hasProperty(RightOperandType.MELT, second.type()) &&
         checker.hasProperty(RightOperandType.HOT, first.type())) {
       second.setState(false);
     }
   }
}
