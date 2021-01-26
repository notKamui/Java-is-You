package com.notkamui.javaisyou.engine.rule.rulepart.property;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

/**
 * Utility class for methods on properties
 */
final class PropertyUtils {

  /**
   * Check and applies the HOT/MELT properties for two elements
   *
   * @param first   the first element to check
   * @param second  the second element to check
   * @param checker the property checker
   */
  static void melting(BoardElement first, BoardElement second, PropertyChecker checker) {
    if (checker.hasProperty(RightOperandType.MELT, first.type()) && checker.hasProperty(RightOperandType.HOT, second.type())) {
      first.setState(false);
    } else if (checker.hasProperty(RightOperandType.MELT, second.type()) &&
        checker.hasProperty(RightOperandType.HOT, first.type())) {
      second.setState(false);
    }
  }
}
