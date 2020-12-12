package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.boardelement.Sprites;

import java.util.*;

final class WordData {
    private final SortedSet<PassiveProperty> passiveProps = new TreeSet<>();
    private final SortedSet<MovementProperty> movementProps = new TreeSet<>();
    private final Set<MovementProperty> defaultMoveProp = Set.of(new MovementProperty.Push());
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

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

    public Set<PassiveProperty> passiveProperties() {
       return Set.copyOf(passiveProps);
    }

    public Set<MovementProperty> movementProperties() {
        var clone = new TreeSet<>(movementProps);
        clone.addAll(defaultMoveProp);
        return clone;
    }

    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        if (prop instanceof MovementProperty moveProp) {
            movementProps.add(moveProp);
        } else if (prop instanceof PassiveProperty passiveProp) {
            passiveProps.add(passiveProp);
        } else {
            throw new RuntimeException("Unknown type of Property");
        }
    }

    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        if (prop instanceof MovementProperty moveProp) {
            movementProps.remove(moveProp);
        } else if (prop instanceof PassiveProperty passiveProp) {
            passiveProps.remove(passiveProp);
        }
    }

    public String getPicture(WordAspect type) {
        Objects.requireNonNull(type);
        return Sprites.OP_PROPS.get(type);
    }
}
