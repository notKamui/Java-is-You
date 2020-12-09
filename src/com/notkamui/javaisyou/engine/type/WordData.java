package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.OpOrProp;
import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.boardelement.Sprites;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

final class WordData {
    private final Set<Property> props = new HashSet<>();
    private final Set<Flag> flags = new HashSet<>();
    private final Set<Property> defaultProps = Set.of(new Property.Push());

    public WordData() {

    }

    public boolean hasFlag(Flag flag) {
        Objects.requireNonNull(flag);
        return flags.contains(flag);
    }

    public void addFlag(Flag flag) {
        Objects.requireNonNull(flag);
        flags.add(flag);
    }

    public void removeFlag(Flag flag) {
        Objects.requireNonNull(flag);
        flags.remove(flag);
    }

    public Set<Property> properties() {
        var clone = new HashSet<>(props);
        clone.addAll(defaultProps);
        return clone;
    }

    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.add(prop);
    }

    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.remove(prop);
    }

    public String getPicture(OpOrProp type) {
        Objects.requireNonNull(type);
        return Sprites.OP_PROPS.get(type);
    }
}
