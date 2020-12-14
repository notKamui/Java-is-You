package com.notkamui.javaisyou.engine.operation;

public interface Operator {
  // TODO ask for global
  static Operator nullOperator() {
    return new Operator() {};
  }

  default OperationResult apply(LeftOperand leftOperand, RightOperand rightOperand) {
    return OperationResult.INEFFECTIVE;
  }

  default OperationResult unapply(LeftOperand leftOperand, RightOperand rightOperand) {
    return OperationResult.INEFFECTIVE;
  }
}
