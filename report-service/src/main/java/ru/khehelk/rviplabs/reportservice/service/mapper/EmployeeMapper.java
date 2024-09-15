package ru.khehelk.rviplabs.reportservice.service.mapper;

import org.mapstruct.Mapper;
import ru.khehelk.rviplabs.reportservice.domain.EmployeeEntity;
import ru.khehelk.rviplabs.reportservice.service.dto.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(EmployeeEntity employeeEntity);

}
