package ru.khehelk.rviplabs.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khehelk.rviplabs.mainservice.domain.QualificationEntity;

@Repository
public interface QualificationRepository extends JpaRepository<QualificationEntity, Long> {

}
