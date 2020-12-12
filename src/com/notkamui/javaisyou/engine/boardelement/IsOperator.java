package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import java.util.Objects;
import java.util.Set;

public final class IsOperator implements Operator {
    private final WordWrapper wordWrapper;
    private final BoardElementComponent component;

    public IsOperator(WordWrapper wordWrapper, Direction dir, int x, int y) {
        Objects.requireNonNull(wordWrapper);
        Objects.requireNonNull(dir);
        this.wordWrapper = wordWrapper;
        this.component = new BoardElementComponent(dir, x, y);
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
        } else if (applicable instanceof TextualProperty textProp){
            noun.representedWrapper().addProperty(textProp.representedProperty());
        } else {
            throw new RuntimeException("Unknown type of Applicable");
        }
    }

    @Override
    public void unapply(Noun noun, Applicable applicable) {
        if (applicable instanceof TextualProperty textProp) {
            noun.representedWrapper().removeProperty(textProp.representedProperty());
        } else if (applicable instanceof Noun) {

        } else {
            throw new RuntimeException("Unknown type of Applicable");
        }
    }

    @Override
    public Set<PropertyFlag> flags() {
        return wordWrapper.flags();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return wordWrapper.movementProperties();
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return wordWrapper.passiveProperties();
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
    public void move(Movement move) {
        component.move(move);
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
