package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.rule.RightOperandType;

public interface PropertyChecker {
  boolean hasProperty(RightOperandType property, long id);
}
