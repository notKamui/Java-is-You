package com.notkamui.javaisyou.engine.rule;

/**
 * Represents the different types of right operands ordered by priority of application
 */
public enum RightOperandType {
    /**
     * Null operand
     */
    NULL_OPERAND,

    /**
     * Type (i.e BABA, ROCK...)
     */
    TYPE,

    /**
     * Stop
     */
    STOP,

    /**
     * Push
     */
    PUSH,

    /**
     * Passive
     */
    YOU,

    /**
     * Sink
     */
    SINK,

    /**
     * Hot
     */
    HOT,

    /**
     * Melt
     */
    MELT,

    /**
     * Boom
     */
    BOOM,

    /**
     * Defeat
     */
    DEFEAT,

    /**
     * Win
     */
    WIN
}
