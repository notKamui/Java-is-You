package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.rule.*;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class manages the rules of a level by updating them and applying them.
 * A RuleManager knows its active rules, default rules, as well as the model of the level.
 */
public final class RuleManager implements PropertyChecker {
  private final List<Rule> rules = new ArrayList<>();
  private final List<Rule> defaultRules;
  private final Model model;

  /**
   * Constructor for RuleManager
   * Initializes the list of active rules.
   *
   * @param model        the model of the level
   * @param defaultRules the default rules of the level (contains at least TEXT IS PUSH)
   */
  public RuleManager(Model model, List<Rule> defaultRules) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(defaultRules);
    this.model = model;
    this.defaultRules = defaultRules;
  }

  private static List<LeftOperand> toLeftOperands(List<BoardElement> elements) {
    return elements.stream()
        .map(e -> e.rulePart().getAsLeftOperand())
        .filter(l -> l != LeftOperand.NULL_LEFT_OPERAND)
        .collect(Collectors.toList());
  }

  private static List<RightOperand> toRightOperands(List<BoardElement> elements) {
    return elements.stream()
        .map(e -> e.rulePart().getAsRightOperand())
        .filter(r -> r != RightOperand.NULL_RIGHT_OPERAND)
        .collect(Collectors.toList());
  }

  private void build(List<LeftOperand> leftOperands, Operator operator, List<RightOperand> rightOperands) {
    Objects.requireNonNull(leftOperands);
    Objects.requireNonNull(operator);
    Objects.requireNonNull(rightOperands);

    rightOperands.forEach(rightOperand -> {
      if (rightOperand.acceptedAsRight(operator)) {
        leftOperands.forEach(leftOperand -> rules.add(new Rule(leftOperand, operator, rightOperand)));
      }
    });
  }

  private void buildOperatorRules(Operator operator, int x, int y) {
    Objects.requireNonNull(operator);
    if (operator == Operator.NULL_OPERATOR) {
      throw new IllegalArgumentException("operator == NULL_OPERATOR");
    }

    var leftList = toLeftOperands(model.elementsAt(x, y - 1));
    var rightList = toRightOperands(model.elementsAt(x, y + 1));
    var upList = toLeftOperands(model.elementsAt(x - 1, y));
    var downList = toRightOperands(model.elementsAt(x + 1, y));

    build(leftList, operator, rightList);
    build(upList, operator, downList);
  }

  private void buildRules() {
    var operators = model.elementsFiltered(e -> e.rulePart().getAsOperator() != Operator.NULL_OPERATOR);
    operators.forEach(operator -> buildOperatorRules(
        operator.rulePart().getAsOperator(),
        operator.x(),
        operator.y()
    ));
    rules.addAll(defaultRules);
  }

  /**
   * Updates the list of active rules by clearing the previous ones and building the new ones.
   */
  void update() {
    rules.clear();
    buildRules();
  }

  /**
   * Getter for the list of rules.
   * (Safe copy)
   *
   * @return the safe copy of the list of rules
   */
  public List<Rule> rules() {
    return List.copyOf(rules);
  }

  /**
   * Getter for the list of rules for a given type
   * (Safe copy)
   *
   * @param type the type to retrieve the rules of
   * @return the safe copy of the list of rules for the given type
   */
  public List<Rule> rulesOf(Type type) {
    return rules.stream()
        .filter(rule -> type.equals(rule.leftOperand()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean hasProperty(RightOperandType property, Type type) {
    return rules.stream()
        .filter(r -> type.equals(r.leftOperand()))
        .map(Rule::rightOperandType)
        .anyMatch(t -> t.equals(property));
  }
}
