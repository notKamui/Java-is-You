package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.property.PropertyFlag;

public interface HasFlag {
  boolean hasFlag(PropertyFlag propertyFlag);

  // Set<PropertyFlag> flags();
}
