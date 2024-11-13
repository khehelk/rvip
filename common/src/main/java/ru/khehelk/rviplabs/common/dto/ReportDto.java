package ru.khehelk.rviplabs.common.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    //private String linkToReportCsvFile;

    private List<EmployeeReportDto> employees;

}
