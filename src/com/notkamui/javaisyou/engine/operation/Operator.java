package com.notkamui.javaisyou.engine.operation;

public interface Operator {
  // TODO ask for global
  static Operator nullOperator() {
    return new Operator() {
      @Override
      public Result apply(LeftOperand leftOperand, RightOperand rightOperand) {
        return Result.INEFFECTIVE;
      }

      @Override
      public Result unapply(LeftOperand leftOperand, RightOperand rightOperand) {
        return Result.INEFFECTIVE;
      }
    };
  }

  Result apply(LeftOperand leftOperand, RightOperand rightOperand);

  Result unapply(LeftOperand leftOperand, RightOperand rightOperand);
}
