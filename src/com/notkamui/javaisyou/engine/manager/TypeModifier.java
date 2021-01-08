package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.rule.LeftOperand;

public interface TypeModifier {
  void modify(LeftOperand oldType, LeftOperand newType);
}
