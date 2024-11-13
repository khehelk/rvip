package ru.khehelk.rviplabs.fileservice.web.rest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.fileservice.service.FileService;
import ru.khehelk.rviplabs.gatewayconfig.filter.TraceIdFilter;

@Slf4j
@RestController
@RequestMapping("api/v1/file")
@RequiredArgsConstructor
public class FileServiceController {

    private final FileService fileService;

    @PostMapping("/report")
    public ResponseEntity<String> saveReportFile(@RequestBody ReportDto reportDto)
        throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException,
        InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.info("Запрос на сохранение отчета в хранилище");
        String messageId = MDC.get(TraceIdFilter.TRACE_ID_KEY);
        var fileName = fileService.save(messageId, reportDto);
        log.info("Отчет успешно сохранен в хранилище");
        return ResponseEntity.ok(fileName);
    }

}
