package org.example;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SimpleAdmin {
    private final static Logger logger = LoggerFactory.getLogger(SimpleAdmin.class);
    private final static String TOPIC_NAME = "java-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        createTopic(TOPIC_NAME, 3, (short) 1);
    }

    public static void createTopic(String name, int numPartitions, short replicationFactory){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic(name, numPartitions, replicationFactory);
        List<NewTopic> newTopicList = new ArrayList<NewTopic>();
        newTopicList.add(newTopic);
        adminClient.createTopics(newTopicList);
        adminClient.close();
    }
}