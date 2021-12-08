package com.ibm.aiops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventSender {
    @Autowired
    private KafkaTemplate<String, EventMessage> kafkaTemplate;

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrap;

	@Value("${kafka-topic.aiops-tpd}")
	private String eventMessageTopic;

    public void sendEventMessage(EventMessage em) {
        kafkaTemplate.send(eventMessageTopic, em);
        log.info("sending info _ event='{}'", em.toString());
    }
}
