package com.notkamui.javaisyou.engine.type;

public sealed interface TransferWrapper extends Wrapper, ElementsSender, ElementsReceiver
        permits EntityWrapper, WordWrapper {

}
