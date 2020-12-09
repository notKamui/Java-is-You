package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.OpOrProp;
import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.EntityType;
import com.notkamui.javaisyou.engine.boardelement.Sprites;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

final class BabaWordTypeData implements BabaTypeData {
    private final EntityType type;
    private final Set<Property> props = new HashSet<>();
    private final Set<Flag> flags = new HashSet<>();
    private final Set<Property> defaultProps = Set.of(new Property.Push());

    public BabaWordTypeData(EntityType type) {
        this.type = type;
    }

    @Override
    public boolean hasFlag(Flag flag) {
        Objects.requireNonNull(flag);
        return flags.contains(flag);
    }

    @Override
    public void addFlag(Flag flag) {
        Objects.requireNonNull(flag);
        flags.add(flag);
    }

    @Override
    public void removeFlag(Flag flag) {
        Objects.requireNonNull(flag);
        flags.remove(flag);
    }

    @Override
    public Set<Property> properties() {
        var clone = new HashSet<>(props);
        clone.addAll(defaultProps);
        return clone;
    }

    @Override
    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.add(prop);
    }

    @Override
    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.remove(prop);
    }

    public String getPicture(OpOrProp type) {
        Objects.requireNonNull(type);
        return Sprites.OP_PROPS.get(type);
    }
}
