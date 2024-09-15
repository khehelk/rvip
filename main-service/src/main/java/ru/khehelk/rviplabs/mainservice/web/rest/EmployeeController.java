package ru.khehelk.rviplabs.mainservice.web.rest;

import java.util.List;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> create(
        @RequestBody EmployeeCreateDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(employeeService.create(employeeDto));
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(
        @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.update(employeeDto));
    }

    @PatchMapping("/{id}/fire")
    public ResponseEntity<EmployeeDto> fire(
        @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.fire(id));
    }

    @PatchMapping("/{id}/add-qualification")
    public ResponseEntity<EmployeeDto> addQualification(
        @PathVariable(name = "id") Long id,
        @RequestParam(name = "qualification") String qualification) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.addQualifications(id, qualification));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(employeeService.getAllSortedById());
    }
}
