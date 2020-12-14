package com.notkamui.javaisyou.engine.operation;

public interface RightOperand {
  // TODO ask for global
  static RightOperand nullRightOperand() {
    return new RightOperand() {};
  }

  default OperationResult applyIsAsRight(LeftOperand leftOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult unapplyIsAsRight(LeftOperand leftOperand) {
    return OperationResult.INEFFECTIVE;
  }
}
