package ru.khehelk.rviplabs.common.dto;

import java.util.List;

public record ReportDto(
    List<EmployeeReportDto> employees
) { }
