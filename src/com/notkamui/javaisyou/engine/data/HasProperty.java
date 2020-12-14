package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyType;

import java.util.Set;

public interface HasProperty {
  Set<MovementProperty> movementProperties();

  Set<PassiveProperty> passiveProperties();

  Set<PropertyType> flags();
}
