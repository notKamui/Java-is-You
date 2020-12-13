package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;

import java.util.SortedSet;

public interface HasProperty {
  SortedSet<MovementProperty> movementProperties();

  SortedSet<PassiveProperty> passiveProperties();
}
