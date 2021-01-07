package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.element.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;

public sealed interface OperationPart permits Operator, RightOperand {
    default boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                           Movement movement, MovementObserver observer) {
        return true;
    }

    default void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {}

    default void onRuleCreation() {}
}
