package com.notkamui.javaisyou.engine.boardelement.word;

import com.notkamui.javaisyou.engine.EntityType;
import com.notkamui.javaisyou.engine.type.BabaType;
import com.notkamui.javaisyou.engine.type.BabaWordType;

import java.util.Objects;

public class Noun {

    private final BabaWordType babaWordType;
    private final BabaType representedType;

    public Noun (BabaWordType babaWordType, BabaType representedType) {
        Objects.requireNonNull(babaWordType);
        Objects.requireNonNull(representedType);
        this.babaWordType = babaWordType;
        this.representedType = representedType;
    }



    public void render() {
        String pic = representedType.getPicture(EntityType.NOUN);
    }

}
