package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.Displayable;
import com.notkamui.javaisyou.engine.rule.LeftOperand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Model implements TypeModifier {
  private final List<BoardElement> elements = new ArrayList<>();

  Model(List<BoardElement> elements) {
    Objects.requireNonNull(elements);
    this.elements.addAll(elements);
  }

  void removeAllDead() {
    elements.forEach(e -> {
      if (!e.state()) {
        elements.remove(e);
      }
    });
  }

  List<BoardElement> elements() {
    return List.copyOf(elements);
  }

  List<Displayable> displayableElements() {
    return List.copyOf(elements);
  }

  private List<BoardElement> elementsFiltered(Predicate<BoardElement> filter) {
    return elements.stream()
            .filter(filter)
            .collect(Collectors.toList());
  }

  List<BoardElement> elementsAt(int x, int y) {
    return elementsFiltered(e -> e.x() == x && e.y() == y);
  }

  @Override
  public void modify(LeftOperand oldType, LeftOperand newType) {
    Objects.requireNonNull(oldType);
    Objects.requireNonNull(newType);
    if (oldType == newType) {
      return;
    }
    var toModify = elementsFiltered(e -> oldType.equals(e.type()));
    toModify.forEach(element -> {
      element.setType(newType.getAsType());
//      if (element.rulePart() != RulePart.NULL_RULE_PART) { // text -> entity
//        element.setRulePart(RulePart.NULL_RULE_PART);
//      }
      // TODO entity -> text
    });
  }
}