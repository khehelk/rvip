package ru.khehelk.rviplabs.mainservice.service.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.khehelk.rviplabs.common.dto.ReportDto;
import ru.khehelk.rviplabs.gatewayconfig.filter.TraceIdFilter;
import ru.khehelk.rviplabs.mainservice.config.properties.KafkaProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaProperties kafkaProperties;

    private final KafkaTemplate<String, ReportDto> kafkaTemplateObject;

    public void sendCsvFile(ReportDto report) {
        Message<ReportDto> reportDtoMessage = MessageBuilder
            .withPayload(report)
            .setHeader(KafkaHeaders.TOPIC, kafkaProperties.getTopicName())
            .setHeader(KafkaHeaders.KEY, MDC.get(TraceIdFilter.TRACE_ID_KEY))
            .build();
        kafkaTemplateObject.send(reportDtoMessage)
            .whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Ошибка при отправке данных: {}", ex.getMessage());
            } else {
                log.info("Данные успешно отправлены: {}", report);
            }
        });
    }

}
