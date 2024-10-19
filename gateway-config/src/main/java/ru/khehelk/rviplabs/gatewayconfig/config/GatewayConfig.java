package ru.khehelk.rviplabs.gatewayconfig.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.khehelk.rviplabs.gatewayconfig.config.properties.GatewayProperties;

@Configuration
@EnableConfigurationProperties(GatewayProperties.class)
public class GatewayConfig {
}
