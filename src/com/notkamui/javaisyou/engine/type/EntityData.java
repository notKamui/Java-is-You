package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

final class EntityData {
    private final String elementPict;
    private final String nounPict;
    private final Set<Property> props = new HashSet<>();
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

    public Set<Property> properties() {
        return props;
    }

    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.add(prop);
    }

    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.remove(prop);
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
                "\nprops=" + props +
                "\npropertyFlags=" + propertyFlags +
                '}';
    }
}

