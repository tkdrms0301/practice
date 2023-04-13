package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;

public class SimpleStreamJoinGlobal {
    private static String APPLICATION_NAME = "streams-application";
    private final static String BOOTSTRAP_SERVER = "localhost:9092";
    private final static String ADDRESS_GLOBAL_TABLE = "address_v2";
    private final static String ORDER_STREAM = "order";
    private final static String ORDER_JOIN_STREAM = "order_join";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        GlobalKTable<String, String> addressGlobalTalb = builder.globalTable(ADDRESS_GLOBAL_TABLE);
        KStream<String, String> orderStream = builder.stream(ORDER_STREAM);
        KStream<String, String> joinStream = orderStream.join(addressGlobalTalb, new KeyValueMapper<String, String, String>() {
            @Override
            public String apply(String orderKey, String orderValue) {
                return orderKey;
            }
        }, new ValueJoiner<String, String, String>() {
            @Override
            public String apply(String order, String address) {
                return order + " send to " + address;
            }
        });
        joinStream.to(ORDER_JOIN_STREAM);
        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();
    }
}
