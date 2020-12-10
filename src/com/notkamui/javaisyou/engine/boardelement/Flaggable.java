package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.property.PropertyFlag;

public interface Flaggable {
    boolean hasFlag(PropertyFlag propertyFlag);

    void addFlag(PropertyFlag propertyFlag);

    void removeFlag(PropertyFlag propertyFlag);
}
