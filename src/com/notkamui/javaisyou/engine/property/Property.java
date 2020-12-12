package com.notkamui.javaisyou.engine.property;

public sealed interface Property extends Comparable<Property> permits
        MovementProperty, PassiveProperty {
    int priority();

    @Override
    default int compareTo(Property other) {
        return this.priority() - other.priority();
    }
}
