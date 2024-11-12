package ru.khehelk.rviplabs.mainservice.web.rest;

import java.util.List;

import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.mainservice.service.EmployeeService;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeDto;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> create(
        @RequestBody EmployeeCreateDto employeeDto) {
        log.info("Запрос на внесение сотрудника в базу");
        var response = ResponseEntity.status(HttpStatus.CREATED)
                             .body(employeeService.create(employeeDto));
        log.info("Сотрудник успешно сохранен в базу");
        return response;
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(
        @RequestBody EmployeeDto employeeDto) {
        MDC.put("employee_id", String.valueOf(employeeDto.id()));
        log.info("Запрос на обновление данных сотрудника");
        var response = ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.update(employeeDto));
        log.info("Данные сотрудника успешно обновлены");
        return response;
    }

    @PatchMapping("/{id}/fire")
    public ResponseEntity<EmployeeDto> fire(
        @PathVariable(name = "id") Long id) {
        MDC.put("employee_id", String.valueOf(id));
        log.info("Запрос на увольнение сотрудника");
        var response = ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.fire(id));
        log.info("Сотрудник успешно уволен");
        return response;
    }

    @PatchMapping("/{id}/add-qualification")
    public ResponseEntity<EmployeeDto> addQualification(
        @PathVariable(name = "id") Long id,
        @RequestParam(name = "qualification") String qualification) {
        MDC.put("employee_id", String.valueOf(id));
        log.info("Запрос на назначение квалификации \"{}\" сотруднику", qualification);
        var response =  ResponseEntity.status(HttpStatus.OK)
            .body(employeeService.addQualifications(id, qualification));
        log.info("Квалификация успешно назначена");
        return response;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        log.info("Запрос на получение общего списка сотрудников");
        return ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.getAllSortedById());
    }
}
