package ru.khehelk.rviplabs.mainservice.service.mapper;

import org.mapstruct.Mapper;
import ru.khehelk.rviplabs.mainservice.domain.QualificationEntity;
import ru.khehelk.rviplabs.mainservice.service.dto.QualificationDto;

@Mapper(componentModel = "spring")
public interface QualificationMapper {

    QualificationDto toDto(QualificationEntity qualificationEntity);

}
