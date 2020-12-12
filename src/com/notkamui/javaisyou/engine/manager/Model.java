package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Model {

  private final List<BoardElement> boardElements = new ArrayList<>();
  private final List<Rule> activeRules = new ArrayList<>();

  Model(List<BoardElement> boardElements) {
    Objects.requireNonNull(boardElements);
    this.boardElements.addAll(boardElements);
  }

  void addRule(Rule rule) {
    Objects.requireNonNull(rule);
    activeRules.add(rule);
  }

  void removeRule(Rule rule) {
    Objects.requireNonNull(rule);
    activeRules.remove(rule);
  }


}
