package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.EntityType;
import com.notkamui.javaisyou.engine.OpOrProp;

import java.util.Map;

public final class Sprites {
    public static final String WALL = "/assets/nouns/WALL/WALL_0.gif";
    public static final String WALL_NOUN = "/assets/nouns/WALL/Text_WALL_0.gif";
    public static final Map<OpOrProp, String> OP_PROPS = Map.of(
            OpOrProp.IS_OPERATOR, "/assets/operators/IS/Op_IS.gif"
    );
}
