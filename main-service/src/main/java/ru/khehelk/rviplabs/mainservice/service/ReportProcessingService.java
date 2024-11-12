package ru.khehelk.rviplabs.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.khehelk.rviplabs.common.dto.ReportDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportProcessingService {

    private final ReportRequestService reportRequestService;

    private final ReportSendService reportSendService;

    public ReportDto processReport(boolean isActive) {
        log.info("Запрос отчета из сервиса report-service");
        var report = reportRequestService.getReport(isActive);
        log.info("Отчет из сервиса report-service успешно получен");
        log.info("Отправляем запрос на сохранение отчета в хранилище");
        var reportLink = reportSendService.saveReportInStorage(report);
        log.info("Отчет успешно сохранен в файл: {}", reportLink);
        report.setLinkToReportCsvFile(reportLink);
        return report;
    }

}
