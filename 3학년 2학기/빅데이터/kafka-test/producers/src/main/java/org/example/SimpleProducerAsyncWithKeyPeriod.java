package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

public class SimpleProducerAsyncWithKeyPeriod {
    private final static Logger logger = LoggerFactory.getLogger(SimpleProducerAsyncWithKeyPeriod.class);
    private final static String TOPIC_NAME = "java-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        String[] keys = {"a01", "b01", "c01", "d01", "e01", "f01", "g01"};
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartition.class.getName());
        properties.setProperty("custom.partioner.hotSpotKey", "01");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for(int i = 0; i < 1000; i++){
            int index = rand.nextInt(keys.length);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, keys[index], "hello java topic" + i);

            // lamda
            producer.send(producerRecord, (RecordMetadata metadata, Exception exception) -> {
                if(exception != null){
                    logger.error(exception.getMessage(), exception);
                }else {
                    logger.info(metadata.toString());
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producer.flush();
        producer.close();
    }
}