package ru.khehelk.rviplabs.mainservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.khehelk.rviplabs.mainservice.config.properties.KafkaProperties;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public NewTopic createTopicIfNotExists() {
        return new NewTopic(kafkaProperties.getTopicName(), kafkaProperties.getTopicPartitions(), (short) 1);
    }

}
