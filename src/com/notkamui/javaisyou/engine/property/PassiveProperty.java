package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;

import java.util.Set;

public sealed interface PassiveProperty extends Property {
  void applyPassive(BoardElement trigger, BoardElement receiver);

  record Melt() implements PassiveProperty {
    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      Properties.melting(trigger, receiver);
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.MELT);
    }

    @Override
    public int priority() {
      return 2;
    }
  }

  record Hot() implements PassiveProperty {
    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      Properties.melting(trigger, receiver);
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.HOT);
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

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
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

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }
  }

  record You() implements PassiveProperty {
    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.YOU);
    }
  }

  //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
