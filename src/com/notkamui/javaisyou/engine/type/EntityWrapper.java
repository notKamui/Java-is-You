package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.boardelement.element.Entity;
import com.notkamui.javaisyou.engine.boardelement.element.Word;
import com.notkamui.javaisyou.engine.data.EntityData;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.OperationResult;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.property.PropertyType;

import javax.swing.*;
import java.util.*;

public final class EntityWrapper implements TransferWrapper {
    private final EntityData data;
    private final ImageIcon nounIcon;
    private final Set<Entity> entities = new HashSet<>();

    public EntityWrapper(ImageIcon elemIcon, ImageIcon nounIcon) {
        Objects.requireNonNull(nounIcon);
        Objects.requireNonNull(elemIcon);
        this.nounIcon = nounIcon;
        this.data = new EntityData(elemIcon);
    }

    // TODO to encapsulate
    public Set<Entity> entities() {
        return new HashSet<>(entities);
    }
    // TODO to encapsulate
    public void removeAllDead() {
        System.out.println(data.movementProperties().size());
        entities.removeIf(e -> !e.state());
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
    public String toString() {
        return data.toString();
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
        rightOperand.receiveEntities(entities);
        entities.clear();
        return OperationResult.NORMAL;
    }

    @Override
    public OperationResult applyIsAsLeft(EntityWrapper rightOperand) {
        Objects.requireNonNull(rightOperand);
        rightOperand.receiveEntities(entities);
        entities.clear();
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

    public void addEntity(Entity entity) {
        Objects.requireNonNull(entity);
        entity.setData(data);
        entities.add(entity);
    }
}
