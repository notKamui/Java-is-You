package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.EntityType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

final class BasicBabaTypeData implements BabaTypeData {
    private final String elementImg;
    private final String nounImg;
    private final Set<Property> props = new HashSet<>();
    private final Set<Flag> flags = new HashSet<>();

    BasicBabaTypeData(String elementImg, String nounImg) {
        Objects.requireNonNull(elementImg);
        Objects.requireNonNull(nounImg);
        this.elementImg = elementImg;
        this.nounImg = nounImg;
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
        return props;
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

    public String getPicture(EntityType type) {
        Objects.requireNonNull(type);
        return switch (type) {
            case NOUN -> nounImg;
            case ELEMENT -> elementImg;
        };
    }

}

