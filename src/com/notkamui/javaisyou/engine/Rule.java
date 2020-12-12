package com.notkamui.javaisyou.engine;

import com.notkamui.javaisyou.engine.boardelement.Applicable;
import com.notkamui.javaisyou.engine.boardelement.Noun;
import com.notkamui.javaisyou.engine.boardelement.Operator;

import java.util.Objects;

public record Rule(Noun noun, Operator operator, Applicable applicable) {
    public void apply() {
        operator.apply(noun, applicable);
    }

    public void unapply() {
        operator.unapply(noun, applicable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return noun.equals(rule.noun) && operator.equals(rule.operator) && applicable.equals(rule.applicable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noun, operator, applicable);
    }

    @Override
    public String toString() {
        return "Rule= " + noun + operator + applicable;
    }
}
