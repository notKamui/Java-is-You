package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Property;

import java.util.Set;

public interface HasProperties {
    Set<Property> properties();

    void addProperty(Property prop);

    void removeProperty(Property prop);
}
