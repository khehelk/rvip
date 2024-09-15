package ru.khehelk.rviplabs.mainservice.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.mainservice.service.ReportRequestService;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportRequestService reportRequestService;

    @GetMapping
    public ResponseEntity<ReportDto> getReport(@RequestParam("isActive") boolean isActive) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(reportRequestService.getReport(isActive));
    }

}
