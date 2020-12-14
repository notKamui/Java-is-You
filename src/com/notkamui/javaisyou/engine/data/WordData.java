package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyType;

import javax.swing.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class WordData implements EditableData {
    private final PropertyComponent component = new PropertyComponent();
    private final Set<MovementProperty> defaultMoveProp = Set.of(new MovementProperty.Push());

    @Override
    public Set<PropertyType> flags() {
        var flags = component.flags();
        defaultMoveProp.forEach(p -> flags.add(p.type()));
        return flags;
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        var clone = component.movementProperties();
        var toAdd = defaultMoveProp.stream()
                .filter(o -> !clone.contains(o))
                .collect(Collectors.toSet());
        clone.addAll(toAdd);
        return clone;
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
       return component.passiveProperties();
    }

    @Override
    public void addProperty(MovementProperty prop) {
        Objects.requireNonNull(prop);
        component.addProperty(prop);
    }

    @Override
    public void addProperty(PassiveProperty prop) {
        Objects.requireNonNull(prop);
        component.addProperty(prop);

    }

    @Override
    public void removeProperty(MovementProperty prop) {
        Objects.requireNonNull(prop);
        component.removeProperty(prop);
    }

    @Override
    public void removeProperty(PassiveProperty prop) {
        Objects.requireNonNull(prop);
        component.removeProperty(prop);
    }

    @Override
    public ImageIcon image() {
        throw new IllegalStateException("wordbehavior hasn't image");
    }
}
