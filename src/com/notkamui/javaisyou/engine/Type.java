package com.notkamui.javaisyou.engine;

public interface Type {
    Type nullType = () -> -1;

    long id();
}
