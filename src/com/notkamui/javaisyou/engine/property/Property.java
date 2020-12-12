package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;

public sealed interface Property extends Comparable<Property>, HasFlag permits MovementProperty, PassiveProperty {
    int priority();

    @Override
    default int compareTo(Property other) {
        return this.priority() - other.priority();
    }

}
