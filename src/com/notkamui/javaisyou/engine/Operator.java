package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;

public interface Operator {
    Operator nullOperator = new Operator() {};

    default boolean onMove(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer) {
        return true;
    }

    default void onSuperposition(BoardElement first, BoardElement second) {}

    default void onRuleCreation() {}
}
