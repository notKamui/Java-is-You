package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.data.HasData;
import com.notkamui.javaisyou.engine.operation.LeftOperand;
import com.notkamui.javaisyou.engine.operation.RightOperand;

public sealed interface Wrapper extends HasData, RightOperand, LeftOperand permits TransferWrapper {}
