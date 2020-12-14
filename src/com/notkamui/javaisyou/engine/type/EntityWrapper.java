package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.element.Entity;
import com.notkamui.javaisyou.engine.boardelement.element.Word;
import com.notkamui.javaisyou.engine.data.EntityBehavior;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Result;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.property.PropertyFlag;

import javax.swing.*;
import java.util.*;

public final class EntityWrapper implements TransferWrapper {
    private final EntityBehavior data;
    private final ImageIcon nounIcon;
    private final Set<Entity> entities = new HashSet<>();

    public EntityWrapper(ImageIcon elemIcon, ImageIcon nounIcon) {
        Objects.requireNonNull(nounIcon);
        Objects.requireNonNull(elemIcon);
        this.nounIcon = nounIcon;
        this.data = new EntityBehavior(elemIcon);
    }

    // TODO to encapsulate
    public Set<Entity> entities() {
        return new HashSet<>(entities);
    }
    // TODO to encapsulate
    public void removeAllDead() {
        entities.removeIf(e -> !e.state());
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
    public String toString() {
        return data.toString();
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
        return leftOperand.applyIsAsLeft(this);
    }


    @Override
    public Result applyIsAsLeft(WordWrapper rightOperand) {
        return Result.ENTITY_TO_TEXT;
    }

    @Override
    public Result applyIsAsLeft(EntityWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        return Result.NORMAL;
    }

    @Override
    public Result applyIsAsLeft(Property rightOperand) {
        Objects.requireNonNull(rightOperand);
        this.data.addProperty(rightOperand);
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
        entities.forEach(this::addEntity);
    }

    @Override
    public void receiveWords(Set<Word> words) {
        Objects.requireNonNull(words);
        words.forEach(word -> {
            var entity = new Entity(word.direction(), word.x(), word.y());
            entity.setData(data);
            entities.add(entity);
        });
    }

    @Override
    public void transferElementsTo(ElementsReceiver receiver) {
        Objects.requireNonNull(receiver);
        receiver.receiveEntities(entities);
        entities.clear();
    }

    public void addEntity(Entity entity) {
        Objects.requireNonNull(entity);
        entity.setData(data);
        entities.add(entity);
    }
}
