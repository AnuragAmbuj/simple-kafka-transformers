## Data Anonymization Transform

This Kafka Connector is a transformer that can be used to 
anonymize data of types PII (Personal Identity & Information), Core Financial (Balances and Transactions) e.t.c while they are in motion.
It is a simple example of how to use the Kafka Connect API to build a custom transformation.



# Running in development

```
mvn clean package
export CLASSPATH="$(find target/ -type f -name '*.jar'| grep '\-package' | tr '\n' ':')"
$CONFLUENT_HOME/bin/connect-standalone $CONFLUENT_HOME/etc/schema-registry/connect-avro-standalone.properties config/MySourceConnector.properties
```
