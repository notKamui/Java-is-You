package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;

public interface EditableProperties extends HasProperty {

  void addProperty(MovementProperty prop);

  void addProperty(PassiveProperty prop);

  void removeProperty(MovementProperty prop);

  void removeProperty(PassiveProperty prop);
}
