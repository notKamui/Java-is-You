package com.notkamui.javaisyou.engine.rule;

public interface Type {
    Type NULL_TYPE = () -> -1;

    long id();
}
