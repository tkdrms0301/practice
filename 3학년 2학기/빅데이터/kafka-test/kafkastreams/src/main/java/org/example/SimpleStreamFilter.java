package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;

import java.util.Properties;

public class SimpleStreamFilter {
    private static String APPLICATION_NAME = "streams-application";
    private final static String BOOTSTRAP_SERVER = "localhost:9092";
    private final static String STREAM_LOG = "stream_log";
    private final static String STREAM_FILTER = "stream_log_filter";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> streamLog = builder.stream(STREAM_LOG);
        KStream<String, String> filteredStream = streamLog.filter(new Predicate<String, String>() {

            @Override
            public boolean test(String key, String value) {
                if (value.length() >= 5)
                    return true;
                else
                    return false;
            }
        });
        filteredStream.to(STREAM_FILTER);

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();
    }
}
