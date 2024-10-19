package ru.khehelk.rviplabs.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.mainservice.config.properties.ReportServiceProperties;

@Service
@RequiredArgsConstructor
public class ReportRequestService {

    private static final String API_VERSION = "/api/v1";
    private static final String REPORT_ENDPOINT = "/reports";

    private final RestTemplate restTemplate;
    
    private final ReportServiceProperties reportServiceProperties;

    public ReportDto getReport(boolean isActive) {
        String url = UriComponentsBuilder.fromHttpUrl(reportServiceProperties.url() + API_VERSION + REPORT_ENDPOINT)
            .queryParam("isActive", isActive)
            .toUriString();
        return restTemplate.getForObject(url, ReportDto.class);
    }

}
