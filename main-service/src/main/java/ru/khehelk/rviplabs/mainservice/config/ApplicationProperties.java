package ru.khehelk.rviplabs.mainservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationProperties(
    String gatewayUrl
) {}
