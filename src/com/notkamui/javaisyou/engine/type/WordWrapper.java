package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.element.Entity;
import com.notkamui.javaisyou.engine.boardelement.element.Noun;
import com.notkamui.javaisyou.engine.boardelement.element.Word;
import com.notkamui.javaisyou.engine.data.WordData;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.OperationResult;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyType;

import javax.swing.*;
import java.util.*;

public final class WordWrapper implements TransferWrapper {
    private final WordData data;
    private final ImageIcon nounIcon;
    private final Set<Word> words = new HashSet<>();
    private Optional<EntityWrapper> nextConversion = Optional.empty();

    public WordWrapper(ImageIcon nounIcon) {
        Objects.requireNonNull(nounIcon);
        this.nounIcon = nounIcon;
        this.data = new WordData();
    }

    // TODO to encapsulate
    public Set<Word> words() {
        return new HashSet<>(words);
    }
    // TODO to encapsulate
    public void removeAllDead() {
        words.removeIf(w -> !w.state());
    }

    @Override
    public Set<PassiveProperty> passiveProperties() {
        return data.passiveProperties();
    }

    @Override
    public Set<MovementProperty> movementProperties() {
        return data.movementProperties();
    }

    @Override
    public Set<PropertyType> flags() {
        return data.flags();
    }

    @Override
    public ImageIcon image() {
        return nounIcon;
    }

    @Override
    public OperationResult applyIsAsRight(LeftOperand leftOperand) {
        Objects.requireNonNull(leftOperand);
        return leftOperand.applyIsAsLeft(this);
    }

    @Override
    public OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
        Objects.requireNonNull(leftOperand);
        return leftOperand.unapplyIsAsLeft(this);
    }

    @Override
    public OperationResult applyIsAsLeft(WordWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        rightOperand.receiveWords(words);
        return OperationResult.NORMAL;
    }

    @Override
    public OperationResult applyIsAsLeft(EntityWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        rightOperand.receiveWords(words);
        return OperationResult.NORMAL;
    }

    @Override
    public OperationResult applyIsAsLeft(PassiveProperty rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.addProperty(rightOperand);
        return OperationResult.NORMAL;
    }

    @Override
    public OperationResult applyIsAsLeft(MovementProperty rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.addProperty(rightOperand);
        return OperationResult.NORMAL;
    }

    @Override
    public OperationResult unapplyIsAsLeft(PassiveProperty rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.removeProperty(rightOperand);
        return OperationResult.NORMAL;
    }

    @Override
    public OperationResult unapplyIsAsLeft(MovementProperty rightOperand) {
        Objects.requireNonNull(rightOperand);
        data.removeProperty(rightOperand);
        return OperationResult.NORMAL;
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

    public void addWord(Word word) {
        Objects.requireNonNull(word);
        word.setData(data);
        words.add(word);
    }
}
