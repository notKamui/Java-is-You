package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.*;

public final class WordBehavior implements EditableData {

    private final Set<PassiveProperty> passiveProps = new TreeSet<>();
    private final Set<MovementProperty> movementProps = new TreeSet<>();
    private final Set<MovementProperty> defaultMoveProp = Set.of(new MovementProperty.Push());
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

    @Override
    public Set<PropertyFlag> flags() {
        return Set.copyOf(propertyFlags);
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        var clone = new HashSet<>(movementProps);
        clone.addAll(defaultMoveProp);
        return clone;
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
       return Set.copyOf(passiveProps);
    }

    @Override
    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        if (prop instanceof MovementProperty moveProp) {
            movementProps.add(moveProp);
        } else if (prop instanceof PassiveProperty passiveProp) {
            passiveProps.add(passiveProp);
        } else {
            throw new RuntimeException("Unknown type of Property");
        }
        propertyFlags.addAll(prop.flags());
    }

    @Override
    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        if (prop instanceof MovementProperty moveProp) {
            movementProps.remove(moveProp);
        } else if (prop instanceof PassiveProperty passiveProp) {
            passiveProps.remove(passiveProp);
        }
        prop.flags().forEach(propertyFlags::remove);
    }

    @Override
    public ImageIcon image() {
        throw new IllegalStateException("wordbehavior hasn't image");
    }
}
