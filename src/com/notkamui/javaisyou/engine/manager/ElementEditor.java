package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;

import java.util.List;
import java.util.function.Predicate;

/**
 * Represents objects that can provide and add BoardElements to itself
 */
public interface ElementEditor {

    /**
     * Getter for a list of elements
     *
     * @return the list of elements
     */
    List<BoardElement> elements();

    /**
     * Getter for a list of elements passed through a filter
     *
     * @param filter the predicate for the filter
     * @return the filtered list of elements
     */
    List<BoardElement> elementsFiltered(Predicate<BoardElement> filter);

    /**
     * Adds an element to itself
     *
     * @param boardElement the element to add
     */
    void addElement(BoardElement boardElement);
}
