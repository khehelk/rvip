package ru.khehelk.rviplabs.fileservice.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.opencsv.CSVWriter;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.fileservice.config.properties.MinioProperties;
import ru.khehelk.rviplabs.gatewayconfig.filter.TraceIdFilter;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioClient minioClient;

    private final MinioProperties minioProperties;

    @PostConstruct
    public void initializeBucket() throws Exception {
        boolean isBucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
            .bucket(minioProperties.bucket())
            .build());
        if (!isBucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(minioProperties.bucket())
                .build());
        }
    }

    public String save(ReportDto reportDto)
        throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException,
        InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.info("Попытка сохранить файл отчета");
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        String[] header = { "Name", "Surname", "IsActive" };
        csvWriter.writeNext(header);

        for (var employee : reportDto.getEmployees()) {
            String[] data = {
                employee.name(),
                employee.surname(),
                Boolean.toString(employee.isActive())
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();

        byte[] csvData = writer.toString().getBytes();
        InputStream inputStream = new ByteArrayInputStream(csvData);

        String fileName = "report_list_" + MDC.get(TraceIdFilter.TRACE_ID_KEY) + ".csv";
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(minioProperties.bucket())
                .object(fileName)
                .stream(inputStream, csvData.length, -1)
                .contentType("text/csv")
                .build()
        );

        inputStream.close();
        log.info("Файл успешно сохранен");
        return fileName;
    }
}
