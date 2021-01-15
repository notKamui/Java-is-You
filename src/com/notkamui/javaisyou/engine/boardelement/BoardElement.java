package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;

import javax.swing.*;
import java.util.Objects;

public final class BoardElement implements LocatedObject, Displayable, Cloneable {
  private boolean isAlive = true;
  private int x;
  private int y;
  private int lastTurnMove = 0;
  private Type type;
  private RulePart rulePart;

  public BoardElement(int x, int y, RulePart rulePart, Type type) {
    Objects.requireNonNull(rulePart);
    Objects.requireNonNull(type);
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    this.x = x;
    this.y = y;
    this.rulePart = rulePart;
    this.type = type;
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

  public void move(Movement move, long turn) {
    Objects.requireNonNull(move);
    if (move.vectorX() == 0 && move.vectorY() == 0) {
      throw new IllegalArgumentException("movement vectors == 0");
    }
    x += move.vectorX();
    y += move.vectorY();
    lastTurnMove++;
  }

  public void setState(boolean state) {
    isAlive = state;
  }

  @Override
  public ImageIcon image() {
    if (rulePart == RulePart.NULL_RULE_PART) {
      return type.elemImage();
    } else {
      return rulePart.image();
    }
  }

  public RulePart rulePart() {
    return rulePart;
  }

  public void setRulePart(RulePart rulePart) {
    Objects.requireNonNull(rulePart);
    this.rulePart = rulePart;
  }

  public Type type() {
    return type;
  }

  public void setType(Type type) {
    Objects.requireNonNull(type);
    this.type = type;
  }

  public int lastTurnMove() {
    return lastTurnMove;
  }

  public BoardElement clone() {
    try {
      return (BoardElement) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }
}
