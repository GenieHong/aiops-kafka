package com.ibm.aiops;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AIOpsController {

    @Autowired
    EventSender eventSender;

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrap;

	@Value("${kafka-topic.aiops-tpd}")
	private String eventMessageTopic;

    @GetMapping("/")
    public String hello() {
        return "Hello AIOps!!";
    }

    @GetMapping("/messages")
    public String sendMessage() {
        log.info("bootstrap server : {} ", bootstrap);
        log.info("topic : {}", eventMessageTopic);


        Sender sender = new Sender();
        sender.setService("CEA SelfMonitoring");
        sender.setName("CEA SelfMonitoring @AGG_P");
        sender.setType("Netcool/OMNIbus");

        Resource resource = new Resource();
        resource.setName("AGG_P");
        resource.setHostname("hostname");
        resource.setType("host");
        resource.setIpaddress("10.1.96.24");
        resource.setLocation("Seoul");

        Type type = new Type();
        type.setClassification(":++:CEASelfMonitoring");
        type.setEventType("problem");

        EventMessage eventMessage = new EventMessage();
        // eventMessage.setDeduplicationKey("EventAnalyticsSelfMonitoring");
        eventMessage.setSender(sender);
        eventMessage.setResource(resource);
        eventMessage.setType(type);
        eventMessage.setSeverity(4);
        eventMessage.setSummary("CEAAnalytics SelfMonitoring Alert");
        eventMessage.setOccurrenceTime(new Date());
        eventMessage.setExpirySeconds(0);

        for (int i=0; i<100; i++) {
            eventMessage.setDeduplicationKey("EventAnalyticsSelfMonitoring" + i);
            eventSender.sendEventMessage(eventMessage);
        }

        return "Message sending is done.";
    }    
}
