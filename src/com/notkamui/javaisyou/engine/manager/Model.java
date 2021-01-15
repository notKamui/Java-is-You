package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.Displayable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

final class Model implements ElementEditor {
  private final List<BoardElement> elements = new ArrayList<>();

  Model(List<BoardElement> elements) {
    Objects.requireNonNull(elements);
    this.elements.addAll(elements);
  }

  void removeAllDead() {
    elements.removeIf(Predicate.not(BoardElement::state));
  }

  @Override
  public List<BoardElement> elements() {
    return List.copyOf(elements);
  }

  List<Displayable> displayableElements() {
    return elements.stream()
        .sorted(Comparator.comparingInt(BoardElement::lastTurnMove))
        .collect(Collectors.toList());
  }

  @Override
  public List<BoardElement> elementsFiltered(Predicate<BoardElement> filter) {
    return elements.stream()
            .filter(filter)
            .collect(Collectors.toList());
  }

  @Override
  public void addElement(BoardElement boardElement) {
    Objects.requireNonNull(boardElement);
    elements.add(boardElement);
  }

  List<BoardElement> elementsAt(int x, int y) {
    return elementsFiltered(e -> e.x() == x && e.y() == y);
  }


}