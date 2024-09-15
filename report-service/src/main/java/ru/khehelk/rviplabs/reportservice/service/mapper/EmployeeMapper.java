package ru.khehelk.rviplabs.reportservice.service.mapper;

import org.mapstruct.Mapper;
import ru.khehelk.rviplabs.common.dto.EmployeeReportDto;
import ru.khehelk.rviplabs.reportservice.domain.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeReportDto toDto(EmployeeEntity employeeEntity);

}
