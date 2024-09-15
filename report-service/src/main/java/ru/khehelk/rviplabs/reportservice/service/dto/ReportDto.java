package ru.khehelk.rviplabs.reportservice.service.dto;

import java.util.List;

public record ReportDto(
    List<EmployeeDto> employees
) { }
