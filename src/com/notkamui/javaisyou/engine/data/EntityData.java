package com.notkamui.javaisyou.engine.data;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyType;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public final class EntityData implements EditableData {
    private final ImageIcon icon;
    private final PropertyComponent component = new PropertyComponent();

    public EntityData(ImageIcon icon) {
        Objects.requireNonNull(icon);
        this.icon = icon;
    }

    @Override
    public ImageIcon image() {
        return icon;
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
       return component.passiveProperties();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return component.movementProperties();
    }

    @Override
    public void addProperty(PassiveProperty prop) {
        Objects.requireNonNull(prop);
        component.addProperty(prop);
    }

    @Override
    public void addProperty(MovementProperty prop) {
        Objects.requireNonNull(prop);
        component.addProperty(prop);
    }

    @Override
    public void removeProperty(MovementProperty prop) {

    }

    @Override
    public void removeProperty(PassiveProperty prop) {

    }

    @Override
    public Set<PropertyType> flags() {
        return component.flags();
    }


}

