package ru.khehelk.rviplabs.mainservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.file-service")
public record FileServiceProperties(
   String url
) {
}
