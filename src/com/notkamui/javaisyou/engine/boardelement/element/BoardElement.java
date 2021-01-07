package com.notkamui.javaisyou.engine.boardelement.element;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.boardelement.Displayable;
import com.notkamui.javaisyou.engine.boardelement.*;

import javax.swing.*;
import java.util.Objects;

public class BoardElement implements Moveable, Stateable, Displayable, HasDirection {
  private RulePart rulePart;
  private final BasicElementComponent component;
  private long id;

  public BoardElement(Direction dir, int x, int y, long id, RulePart rulePart) {
    Objects.requireNonNull(dir);
    Objects.requireNonNull(rulePart);
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    this.component = new BasicElementComponent(dir, x, y);
    this.id = id;
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
  public Direction direction() {
    return component.direction();
  }

  // TODO remove null
  @Override
  public ImageIcon image() {
    return null;
  }

  public RulePart rulePart() {
    return rulePart;
  }

  public long id() {
    return id;
  }


}
