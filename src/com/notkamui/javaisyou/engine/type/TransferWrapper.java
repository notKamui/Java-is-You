package com.notkamui.javaisyou.engine.type;

public sealed interface TransferWrapper extends Wrapper, ElementsReceiver permits EntityWrapper, WordWrapper {}
