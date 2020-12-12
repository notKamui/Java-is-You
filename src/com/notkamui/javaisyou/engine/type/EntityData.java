package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import java.util.*;

final class EntityData {
    private final String elementPict;
    private final String nounPict;
    private final SortedSet<PassiveProperty> passiveProps = new TreeSet<>();
    private final SortedSet<MovementProperty> movementProps = new TreeSet<>();
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

    EntityData(String elementPict, String nounPict) {
        Objects.requireNonNull(elementPict);
        Objects.requireNonNull(nounPict);
        this.elementPict = elementPict;
        this.nounPict = nounPict;
    }

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
    }

    public String getPicture(EntityAspect type) {
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

