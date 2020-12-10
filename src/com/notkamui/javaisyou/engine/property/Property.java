package com.notkamui.javaisyou.engine.property;

import com.notkamui.javaisyou.engine.Movement;
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
            if ((trigger.hasFlag(PropertyFlag.MELT) && receiver.hasFlag(PropertyFlag.HOT)) ||
                    (trigger.hasFlag(PropertyFlag.HOT) && receiver.hasFlag(PropertyFlag.MELT))) {
                trigger.setAlive(false);
                receiver.setAlive(false);
            }
            return true;
        }
    }

   public record Defeat() implements Property {
        @Override
        public boolean apply(Entity trigger, Entity receiver, Movement move) {
            if (trigger.hasFlag(PropertyFlag.YOU)) {
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
