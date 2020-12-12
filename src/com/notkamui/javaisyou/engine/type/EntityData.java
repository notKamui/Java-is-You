package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.*;

final class EntityData implements HasFlag, HasProperty {
    private final ImageIcon elementPict;
    private final ImageIcon nounPict;
    private final SortedSet<PassiveProperty> passiveProps = new TreeSet<>();
    private final SortedSet<MovementProperty> movementProps = new TreeSet<>();
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

    EntityData(ImageIcon elementPict, ImageIcon nounPict) {
        Objects.requireNonNull(elementPict);
        Objects.requireNonNull(nounPict);
        this.elementPict = elementPict;
        this.nounPict = nounPict;
    }


    @Override
    public Set<PropertyFlag> flags() {
        return Set.copyOf(propertyFlags);
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return Set.copyOf(passiveProps);
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return Set.copyOf(movementProps);
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
        propertyFlags.addAll(prop.flags());
    }

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

    public ImageIcon getPicture(EntityAspect type) {
        Objects.requireNonNull(type);
        return switch (type) {
            case NOUN -> nounPict;
            case ELEMENT -> elementPict;
        };
    }

    @Override
    public String toString() {
        return "EntityWrapper{" +
                "\nelementPict='" + elementPict + '\'' +
                "\nnounPict='" + nounPict + '\'' +
                "\nprops=" + passiveProps +
                "\npropertyFlags=" + propertyFlags +
                '}';
    }
}

