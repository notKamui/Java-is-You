package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;

public sealed interface PassiveProperty extends Property {
  void applyPassive(BoardElement trigger, BoardElement receiver);

  record Melting() implements PassiveProperty {
    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      var trigFlags = trigger.flags();
      var recFlags = receiver.flags();
      if ((trigFlags.contains(PropertyFlag.MELT) && recFlags.contains(PropertyFlag.HOT)) ||
              (trigFlags.contains(PropertyFlag.HOT) && recFlags.contains(PropertyFlag.MELT))) {
        trigger.setState(false);
        receiver.setState(false);
      }
    }

    @Override
    public int priority() {
      return 2;
    }
  }

  record Defeat() implements PassiveProperty {
    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      if (trigger.flags().contains(PropertyFlag.YOU)) {
        trigger.setState(false);
      } else {
        receiver.setState(false);
      }
    }

    @Override
    public int priority() {
      return 2;
    }
  }

  record Sink() implements PassiveProperty {
    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      trigger.setState(false);
      receiver.setState(false);
    }

    @Override
    public int priority() {
      return 2;
    }
  }

  //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
