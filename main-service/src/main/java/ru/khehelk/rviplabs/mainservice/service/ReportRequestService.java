package ru.khehelk.rviplabs.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.gatewayconfig.filter.TraceIdFilter;
import ru.khehelk.rviplabs.mainservice.config.properties.ReportServiceProperties;

@Service
@RequiredArgsConstructor
public class ReportRequestService {

    private static final String API_VERSION = "/api/v1";
    private static final String REPORT_ENDPOINT = "/reports";

    private final RestTemplate restTemplate;
    
    private final ReportServiceProperties reportServiceProperties;

    public ReportDto getReport(boolean isActive) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(TraceIdFilter.TRACE_ID_HEADER, MDC.get(TraceIdFilter.TRACE_ID_KEY));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = UriComponentsBuilder.fromHttpUrl(reportServiceProperties.url() + API_VERSION + REPORT_ENDPOINT)
            .queryParam("isActive", isActive)
            .toUriString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, ReportDto.class).getBody();
    }

}
