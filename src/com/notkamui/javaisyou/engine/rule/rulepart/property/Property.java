package com.notkamui.javaisyou.engine.rule.rulepart.property;

import com.notkamui.javaisyou.engine.HasImage;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.Operator;
import com.notkamui.javaisyou.engine.rule.RightOperand;
import com.notkamui.javaisyou.engine.rule.RightOperandType;

import javax.swing.*;
import java.util.Objects;

public sealed interface Property extends RightOperand {

    // Movement
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
            if (checker.hasProperty(RightOperandType.PUSH, receiver.id())) {
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
            return !checker.hasProperty(RightOperandType.STOP, receiver.id());
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
            if (checker.hasProperty(RightOperandType.YOU, first.id())) {
                first.setState(false);
            } else {
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
            first.setState(false);
            second.setState(false);
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

    //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
