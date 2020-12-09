package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.OpOrProp;

import java.util.Map;

public final class Sprites {
    public static final String WALL = "/assets/nouns/WALL/WALL_0.gif";
    public static final String WALL_NOUN = "/assets/nouns/WALL/Text_WALL_0.gif";
    public static final Map<OpOrProp, String> OP_PROPS = Map.of(
            OpOrProp.IS_OPERATOR, "/assets/operators/IS/Op_IS.gif",
            OpOrProp.YOU_PROPERTY, "/assets/properties/YOU/Prop_YOU.gif",
            OpOrProp.WIN_PROPERTY, "/assets/properties/WIN/Prop_WIN.gif",
            OpOrProp.STOP_PROPERTY, "/assets/properties/STOP/Prop_STOP.gif",
            OpOrProp.PUSH_PROPERTY, "/assets/properties/PUSH/Prop_PUSH.gif",
            OpOrProp.MELT_PROPERTY, "/assets/properties/MELT/Prop_MELT.gif",
            OpOrProp.HOT_PROPERTY, "/assets/properties/HOT/Prop_HOT.gif",
            OpOrProp.DEFEAT_PROPERTY, "/assets/properties/DEFEAT/Prop_DEFEAT.gif",
            OpOrProp.SINK_PROPERTY, "/assets/properties/SINK/Prop_SINK.gif"
    );
}
