package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import java.util.Set;

public sealed interface Wrapper permits EntityWrapper, WordWrapper {

    Set<Property> properties();

    void addProperty(Property prop);

    void removeProperty(Property prop);

    boolean hasFlag(PropertyFlag propertyFlag);

    void addFlag(PropertyFlag propertyFlag);

    void removeFlag(PropertyFlag propertyFlag);
}
