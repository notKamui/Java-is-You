package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.Property;

import java.util.Objects;
import java.util.Set;

public final class EntityWrapper {
    private EntityData data;

    public EntityWrapper(String sprite) {
    }


    public EntityData getData() {
        return data;
    }

    public void setData(EntityData data) {
        Objects.requireNonNull(data);
        this.data = data;
    }

    public Set<Property> properties() {
        return data.properties();
    }

    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        data.addProperty(prop);
    }

    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        data.removeProperty(prop);
    }

    public boolean hasFlag(Flag flag) {
        Objects.requireNonNull(flag);
        return data.hasFlag(flag);
    }

    public void addFlag(Flag flag) {
        Objects.requireNonNull(flag);
        data.addFlag(flag);
    }

    public void removeFlag(Flag flag) {
        Objects.requireNonNull(flag);
        data.removeFlag(flag);
    }

    public String getPicture(com.notkamui.javaisyou.engine.EntityType type) {
        Objects.requireNonNull(type);
        return data.getPicture(type);
    }
}
