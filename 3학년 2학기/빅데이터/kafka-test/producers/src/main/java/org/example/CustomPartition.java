package org.example;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.StickyPartitionCache;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.utils.Utils;

import java.util.Map;

public class CustomPartition implements Partitioner {
    private String hotSpotKey;
    private final StickyPartitionCache stickyPartitionCache = new StickyPartitionCache();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int numPartitions = cluster.partitionsForTopic(topic).size();
        if (keyBytes == null){
            return stickyPartitionCache.partition(topic, cluster);
        }

        if (((String) key).equals(hotSpotKey)){
            return 0;
        }
        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        hotSpotKey = (String) configs.get("custom.partioner.hotSpotKey");
    }
}
