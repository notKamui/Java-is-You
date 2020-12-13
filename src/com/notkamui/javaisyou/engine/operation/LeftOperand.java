package com.notkamui.javaisyou.engine.operation;

import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

public interface LeftOperand {
  // TODO ask for global
  static LeftOperand nullLeftOperand() {
    return new LeftOperand() {
      @Override
      public Result applyIsAsLeft(WordWrapper rightOperand) {
        return Result.INEFFECTIVE;
      }

      @Override
      public Result applyIsAsLeft(EntityWrapper rightOperand) {
        return Result.INEFFECTIVE;
      }

      @Override
      public Result applyIsAsLeft(Property rightOperand) {
        return Result.INEFFECTIVE;
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
        return Result.INEFFECTIVE;
      }
    };
  }

  Result applyIsAsLeft(WordWrapper rightOperand);

  Result applyIsAsLeft(EntityWrapper rightOperand);

  Result applyIsAsLeft(Property rightOperand);

  Result unapplyIsAsLeft(WordWrapper rightOperand);

  Result unapplyIsAsLeft(EntityWrapper rightOperand);

  Result unapplyIsAsLeft(Property rightOperand);
}
