package com.notkamui.javaisyou.utils;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.rule.Rule;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.rule.rulepart.HasOperator;
import com.notkamui.javaisyou.engine.rule.rulepart.IsOperator;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class contains methods to safely create board elements types
 */
class GameObjectFactory {
  private final Map<String, RulePart> ruleParts = new HashMap<>();
  private final Map<String, Type> types = new HashMap<>();

  /**
   * Constructor for the GameObjectFactory.
   * Initializes type maps
   */
  GameObjectFactory() {
    types.put("TEXT", new Type(new ImageIcon("resources/assets/nouns/TEXT/Text_TEXT_0.gif"), new ImageIcon()));
    ruleParts.put("NULL_RULE_PART", RulePart.NULL_RULE_PART);
  }

  private static RulePart createRulePart(String part) {
    Objects.requireNonNull(part);
    return switch (part) {
      case "IS" -> new IsOperator();
      case "HAS" -> new HasOperator();

      case "YOU" -> new Property.You();
      case "DEFEAT" -> new Property.Defeat();
      case "SINK" -> new Property.Sink();
      case "HOT" -> new Property.Hot();
      case "MELT" -> new Property.Melt();
      case "WIN" -> new Property.Win();
      case "PUSH" -> new Property.Push();
      case "STOP" -> new Property.Stop();
      case "BOOM" -> new Property.Boom();

      default -> null;
    };
  }

  /**
   * Provides a board element with given types (without instantiating them again)
   *
   * @param x              the x coordinate of the element
   * @param y              the y coordinate of the element
   * @param stringRulePart the rule part type in String format
   * @param stringType     the type in String format
   * @return a new element with the given attributes
   */
  BoardElement provideElement(int x, int y, String stringRulePart, String stringType) {
    Objects.requireNonNull(stringRulePart);
    Objects.requireNonNull(stringType);

    var rulePart = ruleParts.get(stringRulePart);
    if (rulePart == null) {
      rulePart = createRulePart(stringRulePart);
      if (rulePart == null) {
        throw new IllegalArgumentException("unknown rule stringRulePart " + stringRulePart);
      }
      ruleParts.put(stringRulePart, rulePart);
    }
    var type = types.get(stringType);
    if (type == null) {
      throw new IllegalArgumentException("unknown type");
    }
    return new BoardElement(x, y, rulePart, type);
  }

  /**
   * Adds a new type to the type map
   *
   * @param stringType the type in String format
   */
  void addType(String stringType) {
    Objects.requireNonNull(stringType);
    if (types.containsKey(stringType)) {
      return;
    }
    var dir = "resources/assets/nouns/" + stringType + "/";
    var nounPic = new ImageIcon(dir + "Text_" + stringType + "_0.gif");
    var elemPic = new ImageIcon(dir + stringType + "_0.gif");

    var type = new Type(nounPic, elemPic);
    types.put(stringType, type);
    ruleParts.put(stringType, type);
  }

  /**
   * Provides a complete rule with the given parts (without instantiating them again)
   *
   * @param leftOperand  the left operand in String format
   * @param operator     the operator in String format
   * @param rightOperand the right operand in String format
   * @return the created rule
   */
  Rule provideRule(String leftOperand, String operator, String rightOperand) {
    Objects.requireNonNull(leftOperand);
    Objects.requireNonNull(operator);
    Objects.requireNonNull(rightOperand);

    addType(leftOperand);
    var left = types.get(leftOperand);
    RulePart right = types.get(rightOperand);
    if (right == null) { // TODO miam ce code vomitif
      right = createRulePart(rightOperand);
      if (right == null) {
        addType(rightOperand);
        right = types.get(rightOperand);
      }
    }
    return new Rule(left.getAsLeftOperand(), createRulePart(operator).getAsOperator(), right.getAsRightOperand());
  }
}
