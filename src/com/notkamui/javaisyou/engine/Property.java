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
                return false;
            }
            return true;
        }
    }

    record Defeat() implements Property {
        @Override
        public boolean apply(BoardElem trigger, BoardElem receiver, Movement move) {
            if (trigger.hasFlag(Flag.YOU) && receiver.hasFlag(Flag.DEFEAT)) {
                trigger.setAlive(false);
                return false;
            }
            return false;
        }
    }
}
