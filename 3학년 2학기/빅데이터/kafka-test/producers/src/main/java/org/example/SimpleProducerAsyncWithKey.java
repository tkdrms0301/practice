package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleProducerAsyncWithKey {
    private final static Logger logger = LoggerFactory.getLogger(SimpleProducerAsyncWithKey.class);
    private final static String TOPIC_NAME = "java-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for(int i = 0; i < 20; i++){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, String.valueOf(i), "hello java topic" + i);

            // 람다식
            producer.send(producerRecord, (RecordMetadata metadata, Exception exception) -> {
                if(exception != null){
                    logger.error(exception.getMessage(), exception);
                }else {
                    logger.info(metadata.toString());
                }
            });
        }

        producer.flush();
        producer.close();
    }
}