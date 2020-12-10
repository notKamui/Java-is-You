package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.*;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.util.List;
import java.util.Objects;

public final class Entity implements GameObject {
    private EntityWrapper entityWrapper;
    private final GameObjectComponent component;


    public Entity(Board board, EntityWrapper entityWrapper, Direction dir, int x, int y) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(entityWrapper);
        Objects.requireNonNull(dir);
        this.entityWrapper = entityWrapper;
        this.component = new GameObjectComponent(board, this, dir, x, y);
    }

    @Override
    public int x() {
        return component.x();
    }

    @Override
    public int y() {
        return component.y();
    }

    @Override
    public Wrapper wrapper() {
        return (Wrapper) entityWrapper;
    }

    @Override
    public Set<Property> properties() {
        return entityWrapper.properties();
    }

    @Override
    public boolean state() {
        return component.state();
    }

    @Override
    public void setState(boolean state) {
       component.setState(state);
    }

    @Override
    public boolean hasFlag(PropertyFlag propertyFlag) {
        return entityWrapper.hasFlag(propertyFlag);
    }

    @Override
    public void addFlag(PropertyFlag propertyFlag) {
        entityWrapper.addFlag(propertyFlag);
    }

    @Override
    public void removeFlag(PropertyFlag propertyFlag) {
        entityWrapper.removeFlag(propertyFlag);
    }

    @Override
    public boolean move(Movement move) {
        return component.move(move);
    }
}
