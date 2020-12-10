package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.boardelement.GameObject;
import com.notkamui.javaisyou.engine.type.EntityAspect;
import com.notkamui.javaisyou.engine.type.Wrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import java.util.Objects;

public final class Noun implements GameObject, Applicable {

    private final WordWrapper wordWrapper;
    private final Wrapper representedWrapper;

    public Noun (WordWrapper wordWrapper, Wrapper representedWrapper) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(representedWrapper);
        this.wordWrapper = wordWrapper;
        this.representedWrapper = representedWrapper;
    }


    public Wrapper representedWrapper() {
        return representedWrapper;
    }



}
