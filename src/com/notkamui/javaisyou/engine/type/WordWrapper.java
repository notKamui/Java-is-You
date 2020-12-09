package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;
import com.notkamui.javaisyou.engine.OpOrProp;
import com.notkamui.javaisyou.engine.Property;

import java.util.Objects;
import java.util.Set;

public final class WordWrapper {
    private WordData data;

    public WordData getData() {
        return data;
    }

    public void setData(WordData data) {
        Objects.requireNonNull(data);
        this.data = data;
    }

    public Set<Property> properties() {
        return data.properties();
    }

    public void addProperty(Property prop) {
        data.addProperty(prop);
    }

    public void removeProperty(Property prop) {
        data.removeProperty(prop);
    }

    public boolean hasFlag(Flag flag) {
        return data.hasFlag(flag);
    }

    public void addFlag(Flag flag) {
        data.addFlag(flag);
    }

    public void removeFlag(Flag flag) {
        data.removeFlag(flag);
    }

    public String getPicture(OpOrProp type) {
        return data.getPicture(type);
    }

}
