package org.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
public class SimpleProducerAsync {
    private final static Logger logger = LoggerFactory.getLogger(SimpleProducerAsync.class);
    private final static String TOPIC_NAME = "java-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, "hello world");

        // ramda expression
        producer.send(producerRecord, (RecordMetadata metadata, Exception exception) -> {
            if(exception != null){
                logger.error(exception.getMessage(), exception);
            }else {
                logger.info(metadata.toString());
            }
        });

        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception != null){
                    logger.error(exception.getMessage(), exception);
                }else {
                    logger.info(metadata.toString());
                }
            }
        });

        producer.flush();
        producer.close();
    }
}