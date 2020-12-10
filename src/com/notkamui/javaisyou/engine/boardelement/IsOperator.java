package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Board;
import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.util.Objects;
import java.util.Set;

public final class IsOperator implements Operator {
    private final WordWrapper wordWrapper;
    private final GameObjectComponent component;

    public IsOperator(Board board, WordWrapper wordWrapper, Direction dir, int x, int y) {
        Objects.requireNonNull(board);
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(dir);
        this.wordWrapper = wordWrapper;
        this.component = new GameObjectComponent(board, this, dir, x, y);
    }

    @Override
    public void apply(Noun noun, Applicable applicable) {
        if (applicable instanceof Noun otherNoun) {
            var leftWrapper = noun.representedWrapper();
            var rightWrapper = otherNoun.representedWrapper();
            if (leftWrapper instanceof WordWrapper || rightWrapper instanceof WordWrapper) {
                // TODO to implement
                System.out.println("operation currently not implemented");
            } else {
                ((EntityWrapper) leftWrapper).setData(((EntityWrapper) rightWrapper).getData());
            }
        } else { // is Property
            noun.representedWrapper().addProperty(((TextualProperty) applicable).representedProperty());
        }
    }

    @Override
    public void unapply(Noun noun, Applicable applicable) {
        if (applicable instanceof TextualProperty textProp) {
            noun.representedWrapper().removeProperty(textProp.representedProperty());
        }
    }

    @Override
    public boolean hasFlag(PropertyFlag propertyFlag) {
        return wordWrapper.hasFlag(propertyFlag);
    }

    @Override
    public void addFlag(PropertyFlag propertyFlag) {
        wordWrapper.addFlag(propertyFlag);
    }

    @Override
    public void removeFlag(PropertyFlag propertyFlag) {
        wordWrapper.removeFlag(propertyFlag);
    }

    @Override
    public Set<Property> properties() {
        return wordWrapper.properties();
    }

    @Override
    public Wrapper wrapper() {
        return wordWrapper;
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
    public boolean state() {
        return component.state();
    }

    @Override
    public boolean move(Movement move) {
        return component.move(move);
    }

    @Override
    public void setState(boolean state) {
        component.setState(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IsOperator that)) return false;
        return  component.x() == that.x() && component.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(component.x(), component.y());
    }
}
