package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Result;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public sealed interface PassiveProperty extends Property {
  void applyPassive(BoardElement trigger, BoardElement receiver);

  record Melt() implements PassiveProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/MELT/Prop_MELT.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      Properties.melting(trigger, receiver);
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.MELT);
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record Hot() implements PassiveProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/HOT/Prop_HOT.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      Properties.melting(trigger, receiver);
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.HOT);
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record Defeat() implements PassiveProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/PUSH/Prop_DEFEAT.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      if (trigger.flags().contains(PropertyFlag.YOU)) {
        trigger.setState(false);
      } else {
        receiver.setState(false);
      }
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record Sink() implements PassiveProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/SINK/Prop_SINK.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      trigger.setState(false);
      receiver.setState(false);
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of();
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record You() implements PassiveProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/YOU/Prop_YOU.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.YOU);
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  record Win() implements PassiveProperty {
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/WIN/Prop_WIN.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
    }

    @Override
    public int priority() {
      return 3;
    }

    @Override
    public Set<PropertyFlag> flags() {
      return Set.of(PropertyFlag.WIN);
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }
  }

  //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
