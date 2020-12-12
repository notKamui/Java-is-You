package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.Entity;
import com.notkamui.javaisyou.engine.boardelement.IsOperator;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.property.Property;

import java.util.Objects;
import java.util.Set;

public final class EntityWrapper implements Wrapper {
    private EntityData data;

    public EntityWrapper(String elementPict, String nounPict) {
        Objects.requireNonNull(elementPict);
        Objects.requireNonNull(nounPict);
        data = new EntityData(elementPict, nounPict);
    }

    public EntityData getData() {
        return data;
     }

    public void setData(EntityData data) {
        Objects.requireNonNull(data);
        this.data = data;
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return data.passiveProperties();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return data.movementProperties();
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
    public boolean hasFlag(PropertyFlag propertyFlag) {
        Objects.requireNonNull(propertyFlag);
        return data.hasFlag(propertyFlag);
    }

    @Override
    public void addFlag(PropertyFlag propertyFlag) {
        Objects.requireNonNull(propertyFlag);
        data.addFlag(propertyFlag);
    }

    @Override
    public void removeFlag(PropertyFlag propertyFlag) {
        Objects.requireNonNull(propertyFlag);
        data.removeFlag(propertyFlag);
    }


    public String getPicture(EntityAspect type) {
        Objects.requireNonNull(type);
        return data.getPicture(type);
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
