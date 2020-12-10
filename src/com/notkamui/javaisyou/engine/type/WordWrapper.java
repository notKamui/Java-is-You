package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;

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

    public boolean hasFlag(PropertyFlag propertyFlag) {
        return data.hasFlag(propertyFlag);
    }

    public void addFlag(PropertyFlag propertyFlag) {
        data.addFlag(propertyFlag);
    }

    public void removeFlag(PropertyFlag propertyFlag) {
        data.removeFlag(propertyFlag);
    }

    public String getPicture(WordAspect type) {
        return data.getPicture(type);
    }

}
