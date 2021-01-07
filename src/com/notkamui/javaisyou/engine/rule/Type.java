package com.notkamui.javaisyou.engine.rule;

public interface Type {
    Type nullType = () -> -1;

    long id();
}
