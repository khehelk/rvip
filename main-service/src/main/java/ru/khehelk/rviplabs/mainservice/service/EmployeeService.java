package ru.khehelk.rviplabs.mainservice.service;

import java.util.Comparator;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khehelk.rviplabs.mainservice.domain.EmployeeEntity;
import ru.khehelk.rviplabs.mainservice.repository.EmployeeRepository;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeDto;
import ru.khehelk.rviplabs.mainservice.service.mapper.EmployeeMapper;

@Slf4j
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
        var employee = findEmployeeById(id);
        employee.setIsActive(false);
        var savedEmployee = employeeRepository.save(employee);
        return mapper.toDto(savedEmployee);
    }

    @Transactional
    public EmployeeDto addQualifications(Long id, String qualification) {
        var employee = findEmployeeById(id);
        employee.setQualification(qualification);
        return mapper.toDto(employeeRepository.save(employee));
    }

    private EmployeeEntity findEmployeeById(Long id) {
        log.info("Попытка найти сохраненного работника");
        var employee = employeeRepository.getReferenceById(id);
        log.info("Сотрудник найден");
        return employee;
    }

    private void throwIfNotExists(Long id) {
        log.info("Проверка сотрудника на существование в базе");
        if (!employeeRepository.existsById(id)) {
            log.warn("Сотрудник не найден");
            throw new IllegalArgumentException(String.format("Employee with id=%s not found", id));
        }
        log.info("Сотрудник успешно найден в базе");
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllSortedById() {
        log.info("Попытка найти всех работников");
        List<EmployeeDto> employees = employeeRepository.findAll().stream()
                                 .sorted(Comparator.comparing(EmployeeEntity::getId))
                                 .map(mapper::toDto).toList();
        log.info("Список успешно получен");
        return employees;
    }
}
