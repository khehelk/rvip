package ru.khehelk.rviplabs.mainservice.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(value = "kafka")
public class KafkaProperties {

    private final String bootstrapServers;

    private final String topicName;

    private final int topicPartitions;

}
