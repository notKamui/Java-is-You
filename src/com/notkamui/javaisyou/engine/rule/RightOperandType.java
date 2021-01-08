package com.notkamui.javaisyou.engine.rule;

public enum RightOperandType {
  NULL_OPERAND,
  // Type (i.e BABA, ROCK...)
  TYPE,
  // Movement
  STOP, PUSH,
  // Passive
  YOU, SINK, HOT, MELT, DEFEAT, WIN
}
