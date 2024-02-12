package club.ambuj.streams.rules;

import org.apache.kafka.streams.KeyValue;

/**
 * Anonymization rule is an interface that defines the contract for any anonymization rule.
 * It has three methods:<br>
 * 1. <strong>isApplicable(String):</strong> This method checks if the rule is applicable for the given value.<br>
 * 2. <strong>apply(String):</strong> This method applies the rule to the given value.<br>
 * 3. <strong>ruleName():</strong> This method returns the name of the rule.<br>
 * The ruleName is used to identify the rule in the configuration.<br>
 * <br><br>
 * <strong>Lifecycle of a rule:</strong><br>
 * 1. The rule is created and initialized.<br>
 * 2. The rule is added to the GlobalRuleStore.<br>
 * 3. The rule is used by the AnonymizeTransform to anonymize the data.<br>
 */
public interface AnonymizationRule {
    boolean isApplicable(String value);
    String apply(String value);
    String ruleName();

    default void init() {
        GlobalRuleStore.getInstance().addRule(this);
    }
}
