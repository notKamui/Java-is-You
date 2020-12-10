package com.notkamui.javaisyou.engine.boardelement.word;

import com.notkamui.javaisyou.engine.type.EntityAspect;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import java.util.Objects;

public class Noun {

    private final WordWrapper wordWrapper;
    private final EntityWrapper representedType;

    public Noun (WordWrapper wordWrapper, EntityWrapper representedType) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(representedType);
        this.wordWrapper = wordWrapper;
        this.representedType = representedType;
    }



    public void render() {
        String pic = representedType.getPicture(EntityAspect.NOUN);
    }

}
