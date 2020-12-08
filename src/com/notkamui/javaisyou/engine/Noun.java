package com.notkamui.javaisyou.engine;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Noun {
    private final String img;
    private final Set<Property> props = new HashSet<>();
    private final Set<Flag> flags = new HashSet<>();

    public Noun(String img) {
        Objects.requireNonNull(img);
        this.img = img;
    }

    Set<Property> properties() {
        return props;
    }

    void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.add(prop);
    }

    void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        props.remove(prop);
    }

    boolean hasFlag(Flag flag) {
        Objects.requireNonNull(flag);
        return flags.contains(flag);
    }

    void addFlag(Flag flag) {
        Objects.requireNonNull(flag);
        flags.add(flag);
    }

    void removeFlag(Flag flag) {
        Objects.requireNonNull(flag);
        flags.remove(flag);
    }

}

