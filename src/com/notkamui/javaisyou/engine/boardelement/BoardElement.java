package com.notkamui.javaisyou.engine.boardelement;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;

import javax.swing.*;
import java.util.Objects;

/**
 * The representation of an element on the board.
 * A BoardElement knows its coordinates, state, type, and which rule part type it is.
 */
public final class BoardElement implements LocatedObject, Displayable {
  private boolean isAlive = true;
  private int x;
  private int y;
  private int lastTurnMove = 0;
  private Type type;
  private RulePart rulePart;

  /**
   * Constructor for a BoardElement
   *
   * @param x        the x coordinate of the element
   * @param y        the y coordinate of the element
   * @param rulePart the rule part type of the element
   * @param type     the type of the element
   */
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

  /**
   * Moves itself using the given movement.
   *
   * @param move the movement
   */
  public void move(Movement move) {
    Objects.requireNonNull(move);
    if (move.vectorX() == 0 && move.vectorY() == 0) {
      throw new IllegalArgumentException("movement vectors == 0");
    }
    x += move.vectorX();
    y += move.vectorY();
    lastTurnMove++;
  }

  /**
   * Getter for the state of the element
   *
   * @return true if alive, false if dead
   */
  public boolean state() {
    return isAlive;
  }

  /**
   * Sets the state of the element
   *
   * @param state the state to set (true if alive, false if dead)
   */
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

  /**
   * Getter for the rule part of the element
   *
   * @return the rule part of the element
   */
  public RulePart rulePart() {
    return rulePart;
  }

  /**
   * Sets the new rule part of the element
   *
   * @param rulePart the rule part to set
   */
  public void setRulePart(RulePart rulePart) {
    Objects.requireNonNull(rulePart);
    this.rulePart = rulePart;
  }

  /**
   * Getter for the type of the element
   *
   * @return the type of the element
   */
  public Type type() {
    return type;
  }

  /**
   * Sets the new type of the element
   *
   * @param type the type to set
   */
  public void setType(Type type) {
    Objects.requireNonNull(type);
    this.type = type;
  }

  /**
   * Getter for the last turn on which the element moved
   *
   * @return the last turn on which the element moved
   */
  public int lastTurnMove() {
    return lastTurnMove;
  }
}
