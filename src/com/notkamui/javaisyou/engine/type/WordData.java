package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.boardelement.Sprites;

import java.util.*;

final class WordData {
    private final SortedSet<Property> props = new TreeSet<>();
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();
    private final Set<Property> defaultProps = Set.of(new Property.Push());

    public boolean hasFlag(PropertyFlag propertyFlag) {
        Objects.requireNonNull(propertyFlag);
        return propertyFlags.contains(propertyFlag);
    }

    public void addFlag(PropertyFlag propertyFlag) {
        Objects.requireNonNull(propertyFlag);
        propertyFlags.add(propertyFlag);
    }

    public void removeFlag(PropertyFlag propertyFlag) {
        Objects.requireNonNull(propertyFlag);
        propertyFlags.remove(propertyFlag);
    }

    public SortedSet<Property> properties() {
        var clone = new TreeSet<>(props);
        clone.addAll(defaultProps);
        return clone;
    }

    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.add(prop);
    }

    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.remove(prop);
    }

    public String getPicture(WordAspect type) {
        Objects.requireNonNull(type);
        return Sprites.OP_PROPS.get(type);
    }
}
