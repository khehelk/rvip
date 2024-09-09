package ru.khehelk.rviplabs.mainservice.service.dto;

public record EmployeeDto(
    Long id,
    String name,
    String surname,
    boolean isActive
) {}
