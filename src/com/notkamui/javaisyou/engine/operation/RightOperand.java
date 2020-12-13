package com.notkamui.javaisyou.engine.operation;

public interface RightOperand {
  // TODO ask for global
  static RightOperand nullRightOperand() {
    return new RightOperand() {
      @Override
      public Result applyIsAsRight(LeftOperand leftOperand) {
        return Result.INEFFECTIVE;
      }

      @Override
      public Result unapplyIsAsRight(LeftOperand leftOperand) {
        return Result.INEFFECTIVE;
      }
    };
  }

  Result applyIsAsRight(LeftOperand leftOperand);

  Result unapplyIsAsRight(LeftOperand leftOperand);
}
