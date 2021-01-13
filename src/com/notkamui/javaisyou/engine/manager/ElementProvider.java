package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;

import java.util.List;
import java.util.function.Predicate;

public interface ElementProvider {
  List<BoardElement> elements();

  List<BoardElement> elementsFiltered(Predicate<BoardElement> filter);
}
