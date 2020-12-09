package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.Entity;

public sealed interface Property {
    boolean apply(Entity trigger, Entity receiver, Movement move);

    record Push() implements Property {
        @Override
        public boolean apply(Entity trigger, Entity receiver, Movement move) {
            return receiver.move(move);
        }
    }

    public record Melting() implements Property {
        @Override
        public boolean apply(Entity trigger, Entity receiver, Movement move) {
            if ((trigger.hasFlag(Flag.MELT) && receiver.hasFlag(Flag.HOT)) ||
                    (trigger.hasFlag(Flag.HOT) && receiver.hasFlag(Flag.MELT))) {
                trigger.setAlive(false);
                receiver.setAlive(false);
            }
            return true;
        }
    }

   public record Defeat() implements Property {
        @Override
        public boolean apply(Entity trigger, Entity receiver, Movement move) {
            if (trigger.hasFlag(Flag.YOU)) {
                trigger.setAlive(false);
            } else {
                receiver.setAlive(false);
            }
            return true;
        }
    }

    public record Sink() implements Property {
        @Override
        public boolean apply(Entity trigger, Entity receiver, Movement move) {
            trigger.setAlive(false);
            receiver.setAlive(false);
            return true;
        }
    }

    public record Stop() implements Property {
        @Override
        public boolean apply(Entity trigger, Entity receiver, Movement move) {
            return false;
        }
    }

    //TODO Idea : Explosive property (all Explosive explodes after ttl==0)
}
