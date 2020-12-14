package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.Property;

public interface EditableData extends BehaviorData {
  void addProperty(Property prop);

  void removeProperty(Property prop);

}
