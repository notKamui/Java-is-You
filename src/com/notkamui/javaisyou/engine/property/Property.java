package com.notkamui.javaisyou.engine.property;

public sealed interface Property extends Comparable<Property> permits MovementProperty, PassiveProperty, Property.You {
    int priority();

    @Override
    default int compareTo(Property other) {
        return this.priority() - other.priority();
    }

    record You() implements Property {

        @Override
        public int priority() {
            return 0;
        }
    }
}
