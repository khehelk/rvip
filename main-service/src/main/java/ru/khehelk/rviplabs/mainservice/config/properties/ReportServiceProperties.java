package ru.khehelk.rviplabs.mainservice.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.report-service")
public record ReportServiceProperties(
    String url
) { }
