package ru.khehelk.rviplabs.fileservice.config;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.fileservice.config.properties.KafkaProperties;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public NewTopic createTopicIfNotExists() {
        return new NewTopic(kafkaProperties.getTopicName(), kafkaProperties.getTopicPartitions(), (short) 1);
    }

    @Bean
    public Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "file-service");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, ReportDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties(),
            new StringDeserializer(), new JsonDeserializer<>(ReportDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReportDto> jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReportDto> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(5);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

}
