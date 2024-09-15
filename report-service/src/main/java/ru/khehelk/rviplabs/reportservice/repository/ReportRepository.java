package ru.khehelk.rviplabs.reportservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khehelk.rviplabs.reportservice.domain.EmployeeEntity;

@Repository
public interface ReportRepository extends JpaRepository<EmployeeEntity, Long> {

    List<EmployeeEntity> findByIsActive(boolean isActive);

}
