package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;

/**
 * Represents objects that can observe movements of BoardElements and can try to move them
 */
public interface MovementObserver {

    /**
     * Attempts to move a given BoardElement
     *
     * @param movingElement the element to move
     * @param move          the actual move
     * @return true if the move is successful, false otherwise
     */
    boolean tryToMove(BoardElement movingElement, Movement move);
}
