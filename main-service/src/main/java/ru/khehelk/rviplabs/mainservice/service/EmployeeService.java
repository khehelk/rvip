package ru.khehelk.rviplabs.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khehelk.rviplabs.mainservice.domain.EmployeeEntity;
import ru.khehelk.rviplabs.mainservice.repository.EmployeeRepository;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeDto;
import ru.khehelk.rviplabs.mainservice.service.mapper.EmployeeMapper;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    @Transactional
    public EmployeeDto create(EmployeeCreateDto employee) {
        var savedEmployee = employeeRepository.save(mapper.toEntity(employee));
        return mapper.toDto(savedEmployee);
    }

    @Transactional
    public EmployeeDto update(EmployeeDto employee) {
        throwIfNotExists(employee.id());
        var savedEmployee = employeeRepository.save(mapper.toEntity(employee));
        return mapper.toDto(savedEmployee);
    }

    @Transactional
    public EmployeeDto fire(Long id) {
        var employee = employeeRepository.getReferenceById(id);
        employee.setIsActive(false);
        var savedEmployee = employeeRepository.save(employee);
        return mapper.toDto(savedEmployee);
    }

    @Transactional(readOnly = true)
    protected EmployeeEntity get(Long id) {
        return employeeRepository.getReferenceById(id);
    }

    private void throwIfNotExists(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("Employee with id=%s not found", id));
        }
    }
}
