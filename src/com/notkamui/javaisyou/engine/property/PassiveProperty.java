package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.OperationResult;

import javax.swing.*;
import java.util.Objects;
import java.util.Set;

public sealed interface PassiveProperty extends Property {
  void applyPassive(BoardElement trigger, BoardElement receiver);

  record Melt() implements PassiveProperty {
    private final static PropertyType type = PropertyType.MELT;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/MELT/Prop_MELT.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      Properties.melting(trigger, receiver);
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }
  }

  record Hot() implements PassiveProperty {
    private final static PropertyType type = PropertyType.HOT;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/HOT/Prop_HOT.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      Properties.melting(trigger, receiver);
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }
  }

  record Defeat() implements PassiveProperty {
    private final static PropertyType type = PropertyType.DEFEAT;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/DEFEAT/Prop_DEFEAT.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      if (trigger.flags().contains(PropertyType.YOU)) {
        trigger.setState(false);
      } else if (receiver.flags().contains(PropertyType.YOU)) {
        receiver.setState(false);
      }
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public PropertyType type() {
      return type;
    }


    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }
  }

  record Sink() implements PassiveProperty {
    private final static PropertyType type = PropertyType.SINK;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/SINK/Prop_SINK.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
      if (trigger != receiver) {
        trigger.setState(false);
        receiver.setState(false);
      }
    }

    @Override
    public int priority() {
      return 2;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }
  }

  record You() implements PassiveProperty {
    private final static PropertyType type = PropertyType.YOU;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/YOU/Prop_YOU.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
    }

    @Override
    public int priority() {
      return 3;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }
  }

  record Win() implements PassiveProperty {
    private final static PropertyType type = PropertyType.WIN;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/WIN/Prop_WIN.gif");

    @Override
    public void applyPassive(BoardElement trigger, BoardElement receiver) {
    }

    @Override
    public int priority() {
      return 3;
    }

    @Override
    public PropertyType type() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
      Objects.requireNonNull(leftOperand);
      return leftOperand.unapplyIsAsLeft(this);
    }
  }

  //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
