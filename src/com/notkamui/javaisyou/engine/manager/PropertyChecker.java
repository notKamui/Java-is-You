package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.rule.RightOperandType;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;

public interface PropertyChecker {
  boolean hasProperty(RightOperandType property, Type type);
}
