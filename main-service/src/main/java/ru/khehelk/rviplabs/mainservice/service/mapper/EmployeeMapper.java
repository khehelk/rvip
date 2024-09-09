package ru.khehelk.rviplabs.mainservice.service.mapper;

import org.mapstruct.Mapper;
import ru.khehelk.rviplabs.mainservice.domain.EmployeeEntity;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(EmployeeEntity employeeEntity);

    EmployeeEntity toEntity(EmployeeDto employeeDto);

    EmployeeEntity toEntity(EmployeeCreateDto employeeDto);

}
