package ru.khehelk.rviplabs.mainservice.web.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.mainservice.service.QualificationService;
import ru.khehelk.rviplabs.mainservice.service.dto.QualificationCreateDto;
import ru.khehelk.rviplabs.mainservice.service.dto.QualificationDto;

@RestController
@RequestMapping("api/v1/qualification")
@RequiredArgsConstructor
public class QualificationController {

    private final QualificationService qualificationService;

    @PostMapping("/create")
    public ResponseEntity<QualificationDto> create(
        @RequestBody QualificationCreateDto qualificationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(qualificationService.create(qualificationDto));
    }
}
