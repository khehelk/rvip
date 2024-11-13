package ru.khehelk.rviplabs.mainservice.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.mainservice.service.ReportProcessingService;

@Slf4j
@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportProcessingService reportProcessingService;

    @GetMapping
    public ResponseEntity<ReportDto> getReport(@RequestParam("isActive") boolean isActive) {
        log.info("Запрос отчета из сервиса report-service по isActive: {}", isActive);
        var response = ResponseEntity.status(HttpStatus.OK)
                             .body(reportProcessingService.processReport(isActive));
        log.info("Отчет успешно получен из сервиса report-service и сохранен в хранилище");
        return response;
    }

}
