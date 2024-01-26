package com.notkamui.javaisyou.engine;

/**
 * Represents the cardinal directions
 */
public enum Direction {
    /**
     * North
     */
    NORTH {
        @Override
        public Movement vector() {
            return new Movement(0, -1);
        }
    },

    /**
     * West
     */
    WEST {
        @Override
        public Movement vector() {
            return new Movement(-1, 0);
        }
    },

    /**
     * East
     */
    EAST {
        @Override
        public Movement vector() {
            return new Movement(1, 0);
        }
    },

    /**
     * South
     */
    SOUTH {
        @Override
        public Movement vector() {
            return new Movement(0, 1);
        }
    };

    public abstract Movement vector();
}
