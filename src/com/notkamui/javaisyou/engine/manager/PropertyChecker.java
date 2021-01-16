package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.rule.RightOperandType;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;

/**
 * Represents objects that can check if they have a property.
 */
public interface PropertyChecker {

  /**
   * Checks if this has a given property.
   *
   * @param property the property to check
   * @param type     the type of the property
   * @return true if the property is present, false otherwise
   */
  boolean hasProperty(RightOperandType property, Type type);
}
