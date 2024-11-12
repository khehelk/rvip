package ru.khehelk.rviplabs.mainservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.khehelk.rviplabs.mainservice.config.properties.FileServiceProperties;

@Configuration
@EnableConfigurationProperties(FileServiceProperties.class)
public class FileServiceConfig {
}
