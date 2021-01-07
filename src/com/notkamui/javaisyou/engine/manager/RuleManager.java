package com.notkamui.javaisyou.engine.manager;


import com.notkamui.javaisyou.engine.property.OperandType;
import com.notkamui.javaisyou.engine.rule.Rule;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RuleManager implements PropertyChecker {
    private final Map<Long, List<Rule>> idToRules = new HashMap<>();

    void updateRules(Model model) {
        idToRules.clear();
        // TODO
    }

    void applyRules() {
        // TODO
    }

    public List<Rule> rulesOf(long id) {
        final var list = idToRules.get(id);
        return list != null ? List.copyOf(list) : Collections.emptyList();
    }

    @Override
    public boolean hasProperty(OperandType property, long id) {
        return idToRules.getOrDefault(id, Collections.emptyList())
                .stream().map(Rule::rightOperandType)
                .anyMatch(type -> type.equals(property));
    }
}
