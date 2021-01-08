package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.boardelement.Displayable;
import com.notkamui.javaisyou.engine.boardelement.*;

import javax.swing.*;
import java.util.Objects;

public class BoardElement implements LocatedObject, Displayable {
  private RulePart rulePart;
  private long id;
  private boolean isAlive = true;
  private int x;
  private int y;

  public BoardElement(int x, int y, long id, RulePart rulePart) {
    Objects.requireNonNull(rulePart);
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    this.x = x;
    this.y = y;
    this.id = id;
    this.rulePart = rulePart;
  }

  @Override
  public int x() {
    return x;
  }

  @Override
  public int y() {
    return y;
  }

  public boolean state() {
    return isAlive;
  }

  public void move(Movement move) {
    Objects.requireNonNull(move);
    if (move.vectorX() == 0 && move.vectorY() == 0) {
      throw new IllegalArgumentException("movement vectors == 0");
    }

    x += move.vectorX();
    y += move.vectorY();
  }

  public void setState(boolean state) {
    isAlive = state;
  }

  // TODO remove null
  @Override
  public ImageIcon image() {
    return null;
  }

  public RulePart rulePart() {
    return rulePart;
  }

  public void setRulePart(RulePart rulePart) {
    Objects.requireNonNull(rulePart);
    this.rulePart = rulePart;
  }

  public long id() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
