package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.element.Entity;
import com.notkamui.javaisyou.engine.boardelement.element.Word;

import java.util.Set;

public interface ElementsReceiver {
  void receiveEntities(Set<Entity> entities);

  void receiveWords(Set<Word> words);
}
