package com.notkamui.javaisyou.engine.operation;

import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

public interface LeftOperand {
  // TODO ask for global
  static LeftOperand nullLeftOperand() {
    return new LeftOperand(){};
  }

  default OperationResult applyIsAsLeft(WordWrapper rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult applyIsAsLeft(EntityWrapper rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult applyIsAsLeft(MovementProperty rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult applyIsAsLeft(PassiveProperty rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult unapplyIsAsLeft(WordWrapper rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult unapplyIsAsLeft(EntityWrapper rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult unapplyIsAsLeft(PassiveProperty rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult unapplyIsAsLeft(MovementProperty rightOperand) {
    return OperationResult.INEFFECTIVE;
  }
}
