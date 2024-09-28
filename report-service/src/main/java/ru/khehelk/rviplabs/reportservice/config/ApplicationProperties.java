package ru.khehelk.rviplabs.reportservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationProperties(
    String gatewayUrl
) {}
