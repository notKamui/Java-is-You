package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Displayable;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.*;
import com.notkamui.javaisyou.engine.boardelement.element.data.BoardElementData;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.RightOperand;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public class BoardElement implements Moveable, Stateable, HasFlag, HasProperty, Displayable, OperationElement {
  private BoardElementData data;
  private BoardElementComponent component;

  public BoardElement(BoardElementData data, Direction dir, int x, int y) {
    Objects.requireNonNull(dir);
    this.data = data;
    this.component = new BoardElementComponent(dir, x, y);
  }


  public LeftOperand getAsLeft() {
    return data.getAsLeft();
  }

  public RightOperand getAsRight() {
    return data.getAsRight();
  }

  @Override
  public Operator getAsOperator() {
    return data.getAsOperator();
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
  public void move(Movement move) {
    Objects.requireNonNull(move);
    component.move(move);
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
  public Set<PropertyFlag> flags() {
    return data.flags();
  }

  @Override
  public ImageIcon image() {
    return data.image();
  }

  @Override
  public Set<MovementProperty> movementProperties() {
    return data.movementProperties();
  }

  @Override
  public Set<PassiveProperty> passiveProperties() {
    return data.passiveProperties();
  }

  /*
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Entity that)) return false;
    return entityWrapper.equals(that.entityWrapper) &&
            component.x() == that.x() &&
            component.y() == that.y();
  }

  @Override
  public int hashCode() {
    return Objects.hash(component.x(), component.y());
  }*/
}
