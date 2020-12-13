package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.*;

final class EntityData implements HasFlag, HasProperty, HasEntityImage {
    private final ImageIcon elementIcon;
    private final ImageIcon nounIcon;
    private final SortedSet<PassiveProperty> passiveProps = new TreeSet<>();
    private final SortedSet<MovementProperty> movementProps = new TreeSet<>();
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

    EntityData(ImageIcon elementIcon, ImageIcon nounIcon) {
        Objects.requireNonNull(elementIcon);
        Objects.requireNonNull(nounIcon);
        this.elementIcon = elementIcon;
        this.nounIcon = nounIcon;
    }


    @Override
    public Set<PropertyFlag> flags() {
        return Set.copyOf(propertyFlags);
    }

    @Override
    public SortedSet<PassiveProperty> passiveProperties() {
        return SortedSet.copyOf(passiveProps);
    }

    @Override
    public SortedSet<MovementProperty> movementProperties() {
        return SortedSet.copyOf(movementProps);
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

    @Override
    public String toString() {
        return "EntityWrapper{" +
                "\nelementPict='" + elementIcon + '\'' +
                "\nnounPict='" + nounIcon + '\'' +
                "\nprops=" + passiveProps +
                "\npropertyFlags=" + propertyFlags +
                '}';
    }

    @Override
    public ImageIcon entityIcon(EntityAspect aspect) {
        Objects.requireNonNull(aspect);
        return switch (aspect) {
            case NOUN -> nounIcon;
            case ELEMENT -> elementIcon;
            default -> throw new IllegalArgumentException("Unknown aspect");
        };
    }
}

