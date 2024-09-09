package ru.khehelk.rviplabs.mainservice.service.dto;

public record QualificationDto(
    Long id,
    String qualificationName,
    Long employeeId
) {}
