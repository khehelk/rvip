package ru.khehelk.rviplabs.mainservice.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.mainservice.service.EmployeeService;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.EmployeeDto;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> create(
        @RequestBody EmployeeCreateDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(employeeService.create(employeeDto));
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> update(
        @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.update(employeeDto));
    }

    @PatchMapping("/fire")
    public ResponseEntity<EmployeeDto> fire(
        @RequestBody Long id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(employeeService.fire(id));
    }
}
