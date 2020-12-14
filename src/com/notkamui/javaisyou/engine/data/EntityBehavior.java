package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.*;

public final class EntityBehavior implements EditableData {
    private final ImageIcon icon;
    private final List<PassiveProperty> passiveProps = new ArrayList<>();
    private final List<MovementProperty> movementProps = new ArrayList<>();
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

    public EntityBehavior(ImageIcon icon) {
        Objects.requireNonNull(icon);
        this.icon = icon;
    }

    @Override
    public Set<PropertyFlag> flags() {
        return Set.copyOf(propertyFlags);
    }

    @Override
    public List<PassiveProperty> passiveProperties() {
        return List.copyOf(passiveProps);
    }

    @Override
    public List<MovementProperty> movementProperties() {
        return List.copyOf(movementProps);
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
        } else {
            throw new RuntimeException("Unknown type of Property");
        }
        prop.flags().forEach(propertyFlags::remove);
    }

    @Override
    public ImageIcon image() {
        return icon;
    }
}

