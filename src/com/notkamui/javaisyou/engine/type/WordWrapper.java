package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.element.Entity;
import com.notkamui.javaisyou.engine.boardelement.element.Noun;
import com.notkamui.javaisyou.engine.boardelement.element.Word;
import com.notkamui.javaisyou.engine.data.WordBehavior;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Result;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.*;

public final class WordWrapper implements TransferWrapper {
    private final WordBehavior data;
    private final ImageIcon nounIcon;
    private final Set<Word> words = new HashSet<>();
    private Optional<EntityWrapper> nextConversion = Optional.empty();

    public WordWrapper(ImageIcon nounIcon) {
        Objects.requireNonNull(nounIcon);
        this.nounIcon = nounIcon;
        this.data = new WordBehavior();
    }

    @Override
    public SortedSet<PassiveProperty> passiveProperties() {
        return data.passiveProperties();
    }

    @Override
    public SortedSet<MovementProperty> movementProperties() {
        return data.movementProperties();
    }

    @Override
    public Set<PropertyFlag> flags() {
        return data.flags();
    }

    @Override
    public ImageIcon image() {
        return nounIcon;
    }

    @Override
    public Result applyIsAsRight(LeftOperand leftOperand) {
        Objects.requireNonNull(leftOperand);
        return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public Result unapplyIsAsRight(LeftOperand leftOperand) {
        Objects.requireNonNull(leftOperand);
        return leftOperand.unapplyIsAsLeft(this);
    }

    @Override
    public Result applyIsAsLeft(WordWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        rightOperand.transferElementsTo(this);
        return Result.NORMAL;
    }

    @Override
    public Result applyIsAsLeft(EntityWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        nextConversion = Optional.of(rightOperand);
        rightOperand.transferElementsTo(this);
        return Result.NORMAL;
    }

    @Override
    public Result applyIsAsLeft(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.addProperty(rightOperand);
        return Result.NORMAL;
    }

    @Override
    public Result unapplyIsAsLeft(WordWrapper rightOperand) {
        return Result.INEFFECTIVE;
    }

    @Override
    public Result unapplyIsAsLeft(EntityWrapper rightOperand) {
        return Result.INEFFECTIVE;
    }

    @Override
    public Result unapplyIsAsLeft(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.removeProperty(rightOperand);
        return Result.NORMAL;
    }

    @Override
    public void receiveEntities(Set<Entity> entities) {
        Objects.requireNonNull(entities);
        if (nextConversion.isEmpty()) {
            throw new IllegalStateException("no former wrapper for conversion");
        }
        entities.forEach(entity ->{
            var noun = new Noun(entity.direction(), entity.x(), entity.y(), nextConversion.get());
            noun.setData(data);
            words.add(noun);
        });
        nextConversion = Optional.empty();
    }

    @Override
    public void receiveWords(Set<Word> words) {
        Objects.requireNonNull(words);
        words.forEach(this::addWord);
    }

    @Override
    public void transferElementsTo(ElementsReceiver receiver) {
        Objects.requireNonNull(receiver);
        receiver.receiveWords(words);
        words.clear();
    }

    public void addWord(Word word) {
        Objects.requireNonNull(word);
        word.setData(data);
        words.add(word);
    }
}
