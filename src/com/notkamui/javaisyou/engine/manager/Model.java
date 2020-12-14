package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Displayable;
import com.notkamui.javaisyou.engine.Rule;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Model {

  private final List<EntityWrapper> entityWrappers = new ArrayList<>();
  private final WordWrapper wordWrapper;
  private final List<Rule> activeRules = new ArrayList<>();

  Model(List<EntityWrapper> entityWrappers, WordWrapper wordWrapper) {
    Objects.requireNonNull(entityWrappers);
    Objects.requireNonNull(wordWrapper);
    this.entityWrappers.addAll(entityWrappers);
    this.wordWrapper = wordWrapper;
  }

  void addRule(Rule rule) {
    Objects.requireNonNull(rule);
    activeRules.add(rule);
  }

  void removeRule(Rule rule) {
    Objects.requireNonNull(rule);
    activeRules.remove(rule);
  }

  void removeAllDead() {
    entityWrappers.forEach(EntityWrapper::removeAllDead);
    wordWrapper.removeAllDead();
  }

  List<BoardElement> elements() {
    var elements = new ArrayList<BoardElement>();
    entityWrappers.forEach(w -> elements.addAll(w.entities()));
    elements.addAll(wordWrapper.words());
    return elements;
  }

  List<Displayable> displayableElements() {
    return new ArrayList<>(elements());
  }

  List<BoardElement> get(int x, int y) {
    return elements().stream()
            .filter(e -> e.x() == x && e.y() == y)
            .collect(Collectors.toList());
  }
}
