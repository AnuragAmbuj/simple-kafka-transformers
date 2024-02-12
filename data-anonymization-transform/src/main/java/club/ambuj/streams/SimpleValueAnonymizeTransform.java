package club.ambuj.streams;

import club.ambuj.streams.rules.GlobalRuleStore;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;

public class SimpleValueAnonymizeTransform implements ValueTransformer<String, String>{

    private ProcessorContext context;

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public String transform(String value) {
        final StringBuilder stringBuilder = new StringBuilder();
        GlobalRuleStore.getInstance().getRuleNames().forEach(ruleName -> {
            if(GlobalRuleStore.getInstance().getRule(ruleName).isApplicable(value)) {
                stringBuilder.insert(0,GlobalRuleStore.getInstance().getRule(ruleName).apply(value));
            }
        });
        return stringBuilder.toString();
    }

    @Override
    public void close() {
        //Nothing to close here
    }
}
