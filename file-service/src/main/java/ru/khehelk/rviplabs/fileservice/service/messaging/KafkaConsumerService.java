package ru.khehelk.rviplabs.fileservice.service.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.fileservice.service.FileService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final FileService fileService;

    @KafkaListener(topics = "${kafka.topic-name}", containerFactory = "jsonKafkaListenerContainerFactory")
    public void receiveMessage(@Header(name = KafkaHeaders.RECEIVED_KEY) String messageId,
                               @Payload ReportDto reportDto) {
        try {
            fileService.save(messageId, reportDto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
