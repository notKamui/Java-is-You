package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;

class Properties {

   static void melting(BoardElement trigger, BoardElement receiver) {
       var trigFlags = trigger.flags();
       var recFlags = receiver.flags();
       if ((trigFlags.contains(PropertyFlag.MELT) && recFlags.contains(PropertyFlag.HOT)) ||
               (trigFlags.contains(PropertyFlag.HOT) && recFlags.contains(PropertyFlag.MELT))) {
           trigger.setState(false);
           receiver.setState(false);
       }
   }
}
