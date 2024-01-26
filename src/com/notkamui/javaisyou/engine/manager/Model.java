package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.Displayable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class represents the model of a level and serves to encapsulate its state.
 * A Model knows the elements of the level.
 */
final class Model implements ElementEditor {
    private final List<BoardElement> elements = new ArrayList<>();

    /**
     * Constructor for the Model
     *
     * @param elements the list of elements of the level
     */
    Model(List<BoardElement> elements) {
        Objects.requireNonNull(elements);
        this.elements.addAll(elements);
    }

    /**
     * Removes all dead elements (state == false)
     */
    void removeAllDead() {
        elements.removeIf(Predicate.not(BoardElement::state));
    }

    @Override
    public List<BoardElement> elements() {
        return List.copyOf(elements);
    }

    /**
     * The list of elements as Displayable
     * (To preserve encapsulation)
     *
     * @return the list of elements as Displayable ordered by their last turn of movement
     */
    List<Displayable> displayableElements() {
        return elements.stream()
            .sorted(Comparator.comparingInt(BoardElement::lastTurnMove))
            .collect(Collectors.toList());
    }

    @Override
    public List<BoardElement> elementsFiltered(Predicate<BoardElement> filter) {
        return elements.stream()
            .filter(filter)
            .toList();
    }

    @Override
    public void addElement(BoardElement boardElement) {
        Objects.requireNonNull(boardElement);
        elements.add(boardElement);
    }

    /**
     * The list of elements at given coordinates (there can be several elements on the same tile)
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the list of elements at (x,y)
     */
    List<BoardElement> elementsAt(int x, int y) {
        return elementsFiltered(e -> e.x() == x && e.y() == y);
    }


}