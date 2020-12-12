package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.type.WordAspect;

import java.util.Map;

public final class Sprites {
    public static final String WALL = "resources/assets/nouns/WALL/WALL_0.gif";
    public static final String WALL_NOUN = "resources/assets/nouns/WALL/Text_WALL_0.gif";
    public static final String WATER = "resources/assets/nouns/WATER/WATER_0.gif";
    public static final String WATER_NOUN = "resources/assets/nouns/WATER/Text_WATER_0.gif";
    public static final String BABA = "resources/assets/nouns/BABA/BABA_0.gif";
    public static final String BABA_NOUN = "resources/assets/nouns/BABA/Text_BABA_0.gif";

    public static final Map<WordAspect, String> OP_PROPS = Map.of(
            WordAspect.IS_OPERATOR, "resources/assets/operators/IS/Op_IS.gif",
            WordAspect.YOU_PROPERTY, "resources/assets/properties/YOU/Prop_YOU.gif",
            WordAspect.WIN_PROPERTY, "resources/assets/properties/WIN/Prop_WIN.gif",
            WordAspect.STOP_PROPERTY, "resources/assets/properties/STOP/Prop_STOP.gif",
            WordAspect.PUSH_PROPERTY, "resources/assets/properties/PUSH/Prop_PUSH.gif",
            WordAspect.MELT_PROPERTY, "resources/assets/properties/MELT/Prop_MELT.gif",
            WordAspect.HOT_PROPERTY, "resources/assets/properties/HOT/Prop_HOT.gif",
            WordAspect.DEFEAT_PROPERTY, "resources/assets/properties/DEFEAT/Prop_DEFEAT.gif",
            WordAspect.SINK_PROPERTY, "resources/assets/properties/SINK/Prop_SINK.gif"
    );
}
