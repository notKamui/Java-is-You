package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;

public interface RightOperand {
    RightOperand nullRO = new RightOperand() {
    };

    default boolean onMove(BoardElement trigger, BoardElement receiver, Movement movement, MovementObserver observer) {
        return true;
    }

    default void onSuperposition(BoardElement first, BoardElement second) {
    }

    default void onRuleCreation() {
    }
}
