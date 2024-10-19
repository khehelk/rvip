package ru.khehelk.rviplabs.gatewayconfig.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.gateway")
public record GatewayProperties(
    String url
) {}
