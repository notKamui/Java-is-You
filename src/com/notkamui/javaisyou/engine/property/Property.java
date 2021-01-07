package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.HasImage;
import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.rule.RightOperand;

import javax.swing.*;
import java.util.Objects;

public sealed interface Property extends HasImage, RightOperand {

    // Movement
    record Push() implements Property {
        private final static OperandType type = OperandType.PUSH;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/PUSH/Prop_PUSH.gif");@Override
        public OperandType operandType() {
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
            if (checker.hasProperty(OperandType.PUSH, receiver.id())) {
                return observer.tryToMove(receiver, movement);
            } else {
                return true;
            }
        }
    }

    record Stop() implements Property {
        private final static OperandType type = OperandType.STOP;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/STOP/Prop_STOP.gif");

        @Override
        public OperandType operandType() {
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
            return !checker.hasProperty(OperandType.STOP, receiver.id());
        }
    }

    // Passive
    record Melt() implements Property {
        private final static OperandType type = OperandType.MELT;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/MELT/Prop_MELT.gif");

        @Override
        public OperandType operandType() {
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
    }

    record Hot() implements Property {
        private final static OperandType type = OperandType.HOT;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/HOT/Prop_HOT.gif");

        @Override
        public OperandType operandType() {
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
    }

    record Defeat() implements Property {
        private final static OperandType type = OperandType.DEFEAT;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/DEFEAT/Prop_DEFEAT.gif");

        @Override
        public OperandType operandType() {
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
            if (checker.hasProperty(OperandType.YOU, first.id())) {
                first.setState(false);
            } else {
                second.setState(false);
            }
        }

    }

    record Sink() implements Property {
        private final static OperandType type = OperandType.SINK;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/SINK/Prop_SINK.gif");

        @Override
        public OperandType operandType() {
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
    }

    record You() implements Property {
        private final static OperandType type = OperandType.YOU;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/YOU/Prop_YOU.gif");

        @Override
        public OperandType operandType() {
            return type;
        }

        @Override
        public ImageIcon image() {
            return icon;
        }

    }

    record Win() implements Property {
        private final static OperandType type = OperandType.WIN;
        private final static ImageIcon icon = new ImageIcon("resources/assets/properties/WIN/Prop_WIN.gif");

        @Override
        public OperandType operandType() {
            return type;
        }

        @Override
        public ImageIcon image() {
            return icon;
        }
    }

    //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
