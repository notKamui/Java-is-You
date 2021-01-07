package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.Displayable;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Model {
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

  List<BoardElement> getElements(int x, int y) {
    return elements().stream()
            .filter(e -> e.x() == x && e.y() == y)
            .collect(Collectors.toList());
  }


}
