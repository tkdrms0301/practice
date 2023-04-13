package org.example;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConsumerRebalanceListener implements org.apache.kafka.clients.consumer.ConsumerRebalanceListener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerRebalanceListener.class);
    private final KafkaConsumer<String, String> kafkaConsumer;
    private Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    public ConsumerRebalanceListener(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        kafkaConsumer.commitSync(currentOffset);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

    }

    public void addOffsetToTrack(String topic, int partiion, long offset){
        currentOffset.put(
            new TopicPartition(topic, partiion),
            new OffsetAndMetadata(offset, null)
        );
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffset() {
        return currentOffset;
    }
}
