package com.ibm.aiops;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventMessage {
    private String deduplicationKey;
    private Sender sender;
    private Resource resource;
    private Type type;
    private int severity;
    private String summary;
    private Date occurrenceTime;
    private int expirySeconds;
}
