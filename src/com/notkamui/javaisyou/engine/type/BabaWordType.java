package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.OpOrProp;
import com.notkamui.javaisyou.engine.Property;
import com.notkamui.javaisyou.engine.boardelement.Sprites;

import java.util.Objects;
import java.util.Set;

public final class BabaWordType implements BabaType {

    private BabaTypeData data;

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
        data.addProperty(prop);
    }

    @Override
    public void removeProperty(Property prop) {
        data.removeProperty(prop);
    }

    @Override
    public boolean hasFlag(Flag flag) {
        return data.hasFlag(flag);
    }

    @Override
    public void addFlag(Flag flag) {
        data.addFlag(flag);
    }

    @Override
    public void removeFlag(Flag flag) {
        data.removeFlag(flag);
    }

    public String getPicture(OpOrProp type) {
        return data.getPicture(type);
    }

}
