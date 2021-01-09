package com.notkamui.javaisyou.engine.manager;


import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.rule.*;

import java.util.*;
import java.util.stream.Collectors;

public final class RuleManager implements PropertyChecker {
  private final List<Rule> rules = new ArrayList<>();
  private final Model model;

  public RuleManager(Model model) {
    Objects.requireNonNull(model);
    this.model = model;
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
    var operators = model.elements().stream()
            .filter(e -> e.rulePart().getAsOperator() != Operator.NULL_OPERATOR)
            .collect(Collectors.toList());
    operators.forEach(operator -> buildOperatorRules(operator.rulePart().getAsOperator(), operator.x(), operator.y()));
  }

  void update() {
    rules.clear();
    buildRules();
  }

  public List<Rule> rules() {
    return List.copyOf(rules);
  }

  public List<Rule> rulesOf(long id) {
    return rules.stream()
            .filter(rule -> rule.leftOperand().id() == id)
            .collect(Collectors.toList());
  }

  @Override
  public boolean hasProperty(RightOperandType property, long id) {
    return rules.stream()
            .map(Rule::rightOperandType)
            .anyMatch(type -> type.equals(property));
  }
}
