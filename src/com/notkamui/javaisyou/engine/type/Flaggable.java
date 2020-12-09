package com.notkamui.javaisyou.engine.type;

import com.notkamui.javaisyou.engine.Flag;

public interface Flaggable {
    boolean hasFlag(Flag flag);

    void addFlag(Flag flag);

    void removeFlag(Flag flag);
}
