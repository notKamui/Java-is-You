package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.GameObject;

public sealed interface Property extends Comparable<Property> {
    boolean apply(GameObject trigger, GameObject receiver, Movement move);

    int priority();

    @Override
    default int compareTo(Property other) {
        return this.priority() - other.priority();
    }

    record Push() implements Property {
        @Override
        public boolean apply(GameObject trigger, GameObject receiver, Movement move) {
            return receiver.move(move);
        }

        @Override
        public int priority() {
            return 0;
        }
    }

    record Melting() implements Property {
        @Override
        public boolean apply(GameObject trigger, GameObject receiver, Movement move) {
            if ((trigger.hasFlag(PropertyFlag.MELT) && receiver.hasFlag(PropertyFlag.HOT)) ||
                    (trigger.hasFlag(PropertyFlag.HOT) && receiver.hasFlag(PropertyFlag.MELT))) {
                trigger.setState(false);
                receiver.setState(false);
            }
            return true;
        }

        @Override
        public int priority() {
            return 2;
        }
    }

    record Defeat() implements Property {
        @Override
        public boolean apply(GameObject trigger, GameObject receiver, Movement move) {
            if (trigger.hasFlag(PropertyFlag.YOU)) {
                trigger.setState(false);
            } else {
                receiver.setState(false);
            }
            return true;
        }

        @Override
        public int priority() {
            return 2;
        }
    }

    record Sink() implements Property {
        @Override
        public boolean apply(GameObject trigger, GameObject receiver, Movement move) {
            trigger.setState(false);
            receiver.setState(false);
            return true;
        }

        @Override
        public int priority() {
            return 2;
        }
    }

    record Stop() implements Property {
        @Override
        public boolean apply(GameObject trigger, GameObject receiver, Movement move) {
            return false;
        }

        @Override
        public int priority() {
            return 1;
        }
    }

    //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
