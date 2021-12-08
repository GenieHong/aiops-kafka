package com.ibm.aiops;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Sender {
    
    private String service;
    private String name;
    private String type;
}
