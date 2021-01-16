package com.notkamui.javaisyou.engine.rule.rulepart.property;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.ElementEditor;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.LeftOperand;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

import javax.swing.*;
import java.util.Objects;

/**
 * Represents objects that are property types
 * A property knows its type and its images
 */
public sealed interface Property extends RightOperand {
  // Movement

  /**
   * The PUSH property
   * An element with PUSH can be pushed (therefore, cannot be walked over)
   */
  record Push() implements Property {
    private final static RightOperandType type = RightOperandType.PUSH;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/PUSH/Prop_PUSH.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                          Movement movement, MovementObserver observer) {
      Objects.requireNonNull(receiver);
      Objects.requireNonNull(movement);
      Objects.requireNonNull(observer);
      Objects.requireNonNull(checker);
      if (checker.hasProperty(RightOperandType.PUSH, receiver.type())) {
        return observer.tryToMove(receiver, movement);
      } else {
        return true;
      }
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }
  }

  /**
   * The STOP property
   * An element with STOP cannot be walked over
   */
  record Stop() implements Property {
    private final static RightOperandType type = RightOperandType.STOP;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/STOP/Prop_STOP.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                          Movement movement, MovementObserver observer) {
      Objects.requireNonNull(receiver);
      Objects.requireNonNull(checker);
      return !checker.hasProperty(RightOperandType.STOP, receiver.type());
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  // Passive

  /**
   * The MELT property
   * An element with MELT disappears when superimposed with an element with HOT
   */
  record Melt() implements Property {
    private final static RightOperandType type = RightOperandType.MELT;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/MELT/Prop_MELT.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
      Objects.requireNonNull(first);
      Objects.requireNonNull(second);
      Objects.requireNonNull(checker);
      Properties.melting(first, second, checker);
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  /**
   * The HOT property
   * An element with HOT will make a superimposed MELT element disappear
   */
  record Hot() implements Property {
    private final static RightOperandType type = RightOperandType.HOT;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/HOT/Prop_HOT.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
      Objects.requireNonNull(first);
      Objects.requireNonNull(second);
      Objects.requireNonNull(checker);
      Properties.melting(first, second, checker);
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  /**
   * The DEFEAT property
   * An element with DEFEAT will make a superimposed YOU element disappear
   */
  record Defeat() implements Property {
    private final static RightOperandType type = RightOperandType.DEFEAT;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/DEFEAT/Prop_DEFEAT.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
      Objects.requireNonNull(first);
      Objects.requireNonNull(second);
      Objects.requireNonNull(checker);
      if (checker.hasProperty(RightOperandType.YOU, first.type())) {
        first.setState(false);
      } else if (checker.hasProperty(RightOperandType.YOU, second.type())) {
        second.setState(false);
      }
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  /**
   * The SINK property
   * An elements with SINK will make any superimposed element disappear along with itself
   */
  record Sink() implements Property {
    private final static RightOperandType type = RightOperandType.SINK;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/SINK/Prop_SINK.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
      Objects.requireNonNull(first);
      Objects.requireNonNull(second);
      if (first != second) {
        first.setState(false);
        second.setState(false);
      }
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  /**
   * The YOU property
   * An element with YOU can be controlled by the player,
   * will disappear if superimposed with a DEFEAT element,
   * and will make the state of the level as won if superimposed with a WIN element
   */
  record You() implements Property {
    private final static RightOperandType type = RightOperandType.YOU;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/YOU/Prop_YOU.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  /**
   * The WIN property
   * An element with WIN will make the state of the level as won if superimposed with a YOU element
   */
  record Win() implements Property {
    private final static RightOperandType type = RightOperandType.WIN;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/WIN/Prop_WIN.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }
  }

  /**
   * The BOOM property
   * An element with BOOM will instantly disappear, along with all elements directly next to it
   */
  record Boom() implements Property {
    private final static RightOperandType type = RightOperandType.BOOM;
    private final static ImageIcon icon = new ImageIcon("resources/assets/properties/BOOM/Prop_BOOM.gif");

    @Override
    public RightOperandType operandType() {
      return type;
    }

    @Override
    public ImageIcon image() {
      return icon;
    }

    @Override
    public RightOperand getAsRightOperand() {
      return this;
    }

    @Override
    public boolean acceptedAsRight(Operator operator) {
      Objects.requireNonNull(operator);
      return operator.acceptAsRight(this);
    }

    @Override
    public void onRuleCreation(LeftOperand leftOperand, RightOperand rightOperand, ElementEditor provider) {
      Objects.requireNonNull(leftOperand);
      Objects.requireNonNull(provider);
      var bombs = provider.elementsFiltered(e -> leftOperand.getAsType().equals(e.type()));
      bombs.forEach(bomb -> {
        var x = bomb.x();
        var y = bomb.y();
        provider.elementsFiltered(e ->
            x - e.x() >= -1 && x - e.x() <= 1 &&
                y - e.y() >= -1 && y - e.y() <= 1
        ).forEach(e -> e.setState(false));
        bomb.setState(false);
      });
    }
  }
}
