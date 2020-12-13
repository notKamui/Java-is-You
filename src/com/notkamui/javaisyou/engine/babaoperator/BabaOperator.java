package com.notkamui.javaisyou.engine.babaoperator;

import com.notkamui.javaisyou.engine.boardelement.HasImage;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.Operator;
import com.notkamui.javaisyou.engine.operation.Result;
import com.notkamui.javaisyou.engine.operation.RightOperand;

import javax.swing.*;
import java.util.Objects;

public sealed interface BabaOperator extends HasImage, Operator {
  record Is() implements BabaOperator {
    private static final ImageIcon icon = new ImageIcon("resources/assets/operators/IS/Op_IS.gif");

    @Override
    public Result apply(LeftOperand leftOperand, RightOperand rightOperand) {
      Objects.requireNonNull(leftOperand);
      Objects.requireNonNull(rightOperand);
      return rightOperand.applyIsAsRight(leftOperand);
    }

    @Override
    public Result unapply(LeftOperand leftOperand, RightOperand rightOperand) {
      Objects.requireNonNull(leftOperand);
      Objects.requireNonNull(rightOperand);
      return rightOperand.unapplyIsAsRight(leftOperand);
    }

    @Override
    public ImageIcon image() {
      return icon;
    }
  }
}
