package ru.khehelk.rviplabs.mainservice.service;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.gatewayconfig.filter.TraceIdFilter;
import ru.khehelk.rviplabs.mainservice.config.properties.FileServiceProperties;
import ru.khehelk.rviplabs.mainservice.config.properties.ReportServiceProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportSendService {

    private static final String API_VERSION = "/api/v1";
    private static final String REPORT_SAVE_ENDPOINT = "/file/report";

    private final RestTemplate restTemplate;

    private final FileServiceProperties fileServiceProperties;

    public String saveReportInStorage(ReportDto report) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(TraceIdFilter.TRACE_ID_HEADER, MDC.get(TraceIdFilter.TRACE_ID_KEY));

        HttpEntity<ReportDto> entity = new HttpEntity<>(report, headers);

        String url = fileServiceProperties.url() + API_VERSION + REPORT_SAVE_ENDPOINT;
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
    }
}
