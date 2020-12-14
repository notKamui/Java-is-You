package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;

import java.util.List;

public interface HasProperty {
  List<MovementProperty> movementProperties();

  List<PassiveProperty> passiveProperties();
}
