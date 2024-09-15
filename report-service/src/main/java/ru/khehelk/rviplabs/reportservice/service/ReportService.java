package ru.khehelk.rviplabs.reportservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.reportservice.repository.ReportRepository;
import ru.khehelk.rviplabs.reportservice.service.mapper.EmployeeMapper;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public ReportDto getEmployeeReport(boolean isActive) {
        var employees = reportRepository.findByIsActive(isActive);
        return new ReportDto(
            employees.stream().map(employeeMapper::toDto).toList()
        );
    }

}
