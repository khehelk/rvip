package ru.khehelk.rviplabs.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khehelk.rviplabs.mainservice.domain.QualificationEntity;
import ru.khehelk.rviplabs.mainservice.repository.QualificationRepository;
import ru.khehelk.rviplabs.mainservice.service.dto.QualificationCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.QualificationDto;
import ru.khehelk.rviplabs.mainservice.service.mapper.QualificationMapper;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;
    private final EmployeeService employeeService;
    private final QualificationMapper mapper;

    @Transactional
    public QualificationDto create(QualificationCreateDto qualificationDto) {
        var employee = employeeService.get(qualificationDto.employeeId());
        var qualification = new QualificationEntity();
        qualification.setEmployee(employee);
        qualification.setQualificationName(qualificationDto.qualificationName());
        return mapper.toDto(qualificationRepository.save(qualification));
    }
}

