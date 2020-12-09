package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.EntityType;

import java.util.Objects;
import java.util.Set;

public final class BasicBabaType implements BabaType {
    private BasicBabaTypeData data;

    public BasicBabaType(String sprite) {
    }


    @Override
    public BabaTypeData getData() {
        return data;
    }

    @Override
    public void setData(BabaTypeData data) {
        Objects.requireNonNull(data);
        this.data = data;
    }

    @Override
    public Set<Property> properties() {
        return data.properties();
    }

    @Override
    public void addProperty(Property prop) {
        Objects.requireNonNull(prop);
        data.addProperty(prop);
    }

    @Override
    public void removeProperty(Property prop) {
        Objects.requireNonNull(prop);
        data.removeProperty(prop);
    }

    @Override
    public boolean hasFlag(Flag flag) {
        Objects.requireNonNull(flag);
        return data.hasFlag(flag);
    }

    @Override
    public void addFlag(Flag flag) {
        Objects.requireNonNull(flag);
        data.addFlag(flag);
    }

    @Override
    public void removeFlag(Flag flag) {
        Objects.requireNonNull(flag);
        data.removeFlag(flag);
    }

    @Override
    public String getPicture(EntityType type) {
        Objects.requireNonNull(type);
        return data.getPicture(type);
    }
}
