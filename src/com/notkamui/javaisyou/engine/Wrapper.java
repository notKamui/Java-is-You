package com.notkamui.javaisyou.engine;

public class Wrapper implements Type, RightOperand, RulePart {
    private final long id;

    public Wrapper(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id < 0");
        }
        this.id = id;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public Type getAsType() {
        return this;
    }

    @Override
    public RightOperand getAsRightOperand() {
        return this;
    }
}
