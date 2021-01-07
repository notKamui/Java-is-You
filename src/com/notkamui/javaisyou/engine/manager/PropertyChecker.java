package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.property.OperandType;

public interface PropertyChecker {
  boolean hasProperty(OperandType property, long id);
}
