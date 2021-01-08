package com.notkamui.javaisyou.engine.rule;

import com.notkamui.javaisyou.engine.Movement;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.MovementObserver;
import com.notkamui.javaisyou.engine.manager.PropertyChecker;
import com.notkamui.javaisyou.engine.manager.TypeModifier;

import java.util.Objects;

public record Rule(LeftOperand leftOperand, Operator operator, RightOperand rightOperand) {
  public Rule {
    Objects.requireNonNull(leftOperand);
    Objects.requireNonNull(operator);
    Objects.requireNonNull(rightOperand);
  }

  public RightOperandType rightOperandType() {
    return rightOperand.operandType();
  }

  public boolean onMove(BoardElement trigger, BoardElement receiver, PropertyChecker checker,
                        Movement movement, MovementObserver observer) {
    Objects.requireNonNull(trigger);
    Objects.requireNonNull(receiver);
    Objects.requireNonNull(movement);
    Objects.requireNonNull(observer);
    return operator.onMove(rightOperand, trigger, receiver, checker, movement, observer);
  }

  public void onSuperposition(BoardElement first, BoardElement second, PropertyChecker checker) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);
    Objects.requireNonNull(checker);
    operator.onSuperposition(rightOperand, first, second, checker);
  }

  public void onRuleCreation(TypeModifier modifier) {
    Objects.requireNonNull(modifier);
    operator.onRuleCreation(leftOperand, rightOperand, modifier);
  }
}
