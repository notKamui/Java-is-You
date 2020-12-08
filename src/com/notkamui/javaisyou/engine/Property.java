package com.notkamui.javaisyou.engine;

public sealed interface Property {
    boolean apply(BoardElem trigger, BoardElem receiver, Movement move);

    record Push() implements Property {
        @Override
        public boolean apply(BoardElem trigger, BoardElem receiver, Movement move) {
            return receiver.move(move);
        }
    }

    record Melting() implements Property {
        @Override
        public boolean apply(BoardElem trigger, BoardElem receiver, Movement move) {
            if ((trigger.hasFlag(Flag.MELT) && receiver.hasFlag(Flag.HOT)) ||
                    (trigger.hasFlag(Flag.HOT) && receiver.hasFlag(Flag.MELT))) {
                trigger.setAlive(false);
                receiver.setAlive(false);
            }
            return true;
        }
    }

    record Defeat() implements Property {
        @Override
        public boolean apply(BoardElem trigger, BoardElem receiver, Movement move) {
            if (trigger.hasFlag(Flag.YOU)) {
                trigger.setAlive(false);
            } else {
                receiver.setAlive(false);
            }
            return true;
        }
    }

    record Sink() implements Property {
        @Override
        public boolean apply(BoardElem trigger, BoardElem receiver, Movement move) {
            trigger.setAlive(false);
            receiver.setAlive(false);
            return true;
        }
    }

    record Stop() implements Property {
        @Override
        public boolean apply(BoardElem trigger, BoardElem receiver, Movement move) {
            return false;
        }
    }

}
