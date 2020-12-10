package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;

import java.util.Objects;
import java.util.Set;

public final class WordWrapper implements Wrapper {
    private WordData data;

    public WordData getData() {
        return data;
    }

    public void setData(WordData data) {
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
    public boolean hasFlag(PropertyFlag propertyFlag) {
        return data.hasFlag(propertyFlag);
    }

    @Override
    public void addFlag(PropertyFlag propertyFlag) {
        data.addFlag(propertyFlag);
    }

    @Override
    public void removeFlag(PropertyFlag propertyFlag) {
        data.removeFlag(propertyFlag);
    }

    public String getPicture(WordAspect type) {
        return data.getPicture(type);
    }

}
