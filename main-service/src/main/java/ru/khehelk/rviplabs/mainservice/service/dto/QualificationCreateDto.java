package ru.khehelk.rviplabs.mainservice.service.dto;

public record QualificationCreateDto(
    String qualificationName,
    Long employeeId
) {}
