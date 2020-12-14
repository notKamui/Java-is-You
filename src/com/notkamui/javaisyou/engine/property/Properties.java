package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;

class Properties {

   static void melting(BoardElement trigger, BoardElement receiver) {
       var trigFlags = trigger.flags();
       var recFlags = receiver.flags();
       if (trigFlags.contains(PropertyType.MELT) && recFlags.contains(PropertyType.HOT)) {
           trigger.setState(false);
       } else if (trigFlags.contains(PropertyType.HOT) && recFlags.contains(PropertyType.MELT)){
           receiver.setState(false);
       }
   }
}
