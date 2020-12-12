package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.HasFlag;
import com.notkamui.javaisyou.engine.boardelement.HasProperty;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.boardelement.Sprites;

import java.util.*;

final class WordData implements HasProperty, HasFlag {
    private final SortedSet<PassiveProperty> passiveProps = new TreeSet<>();
    private final SortedSet<MovementProperty> movementProps = new TreeSet<>();
    private final Set<MovementProperty> defaultMoveProp = Set.of(new MovementProperty.Push());
    private final Set<PropertyFlag> propertyFlags = new HashSet<>();

    public Set<PropertyFlag> flags() {
        return Set.copyOf(propertyFlags);
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
