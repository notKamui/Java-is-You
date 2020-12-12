package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.boardelement.Applicable;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.Noun;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

  List<BoardElement> elements() {
    return new ArrayList<>(boardElements);
  }


  List<BoardElement> get(int x, int y) {
    return boardElements.stream()
            .filter(e -> e.x() == x && e.y() == y)
            .collect(Collectors.toList());
  }

  Optional<Noun> getNoun(int x, int y) {
    return get(x, y)
            .stream()
            .filter(e -> e instanceof Noun)
            .map(e -> (Noun) e)
            .findFirst();
  }

  Optional<Applicable> getApplicable(int x, int y) {
    return get(x, y)
            .stream()
            .filter(e -> e instanceof Applicable)
            .map(e -> (Applicable) e)
            .findFirst();
  }


}
