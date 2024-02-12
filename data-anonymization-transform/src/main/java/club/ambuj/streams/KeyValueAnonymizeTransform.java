package club.ambuj.streams;

import club.ambuj.streams.rules.GlobalRuleStore;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;

public class KeyValueAnonymizeTransform implements Transformer<String, String, KeyValue<String, String>>{

    private ProcessorContext processorContext;

    @Override
    public void init(ProcessorContext processorContext) {
        this.processorContext = processorContext;
    }

    @Override
    public KeyValue<String, String> transform(final String key, final String value) {
        //Iterating over all the rules and applying them to the value
        GlobalRuleStore.getInstance().getRuleNames().forEach(ruleName -> {
            if(GlobalRuleStore.getInstance().getRule(ruleName).isApplicable(value)) {
                String newValue = GlobalRuleStore.getInstance().getRule(ruleName).apply(value);
                processorContext.forward(key, newValue);
            }
        });
        //Returning null as we are not interested in the original key-value pair
        return null;
    }

    @Override
    public void close() {
        //Nothing to close here
    }
}
