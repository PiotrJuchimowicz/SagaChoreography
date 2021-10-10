package com.company.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KafkaConsumerConfig<T> {

    private static final String SPRING_JACKSON_TRUSTED_PACKAGES_PROPERTY = "spring.json.trusted.packages";
    private final String brokerURL;
    private final String groupId;

    public KafkaConsumerConfig(String brokerURL, String groupId) {
        this.brokerURL = brokerURL;
        this.groupId = groupId;
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, T>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    private ConsumerFactory<String, T> consumerFactory() {
        Map<String, Object> consumerProperties = new HashMap<>();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerURL);
        consumerProperties.put("client.id", UUID.randomUUID().toString());
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProperties.put(SPRING_JACKSON_TRUSTED_PACKAGES_PROPERTY, "*");
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }

}
