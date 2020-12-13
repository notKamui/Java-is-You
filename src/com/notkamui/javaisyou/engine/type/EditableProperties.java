package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.Property;

public interface EditableProperties extends HasProperty {
  void addProperty(Property prop);

  void removeProperty(Property prop);
}
