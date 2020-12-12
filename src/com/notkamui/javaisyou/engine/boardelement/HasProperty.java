package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;

import java.util.Set;

public interface HasProperty {
  Set<MovementProperty> movementProperties();

  Set<PassiveProperty> passiveProperties();
}
