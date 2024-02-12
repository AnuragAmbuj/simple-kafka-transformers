package club.ambuj.streams.rules;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GlobalRuleStore {
    @Getter
    private static final GlobalRuleStore instance = new GlobalRuleStore();
    private final Map<String, AnonymizationRule> rules = new HashMap<>();

    private GlobalRuleStore() {
    }

    public void addRule(AnonymizationRule rule) {
        rules.put(rule.ruleName(), rule);
    }

    public AnonymizationRule getRule(String ruleName) {
        return rules.get(ruleName);
    }

    public Set<String> getRuleNames() {
        return rules.keySet();
    }
}
