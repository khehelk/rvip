package ru.khehelk.rviplabs.reportservice.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.reportservice.service.ReportService;
import ru.khehelk.rviplabs.reportservice.service.dto.ReportDto;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<ReportDto> getReport(@RequestParam("isActive") boolean isActive) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(reportService.getEmployeeReport(isActive));
    }

}
