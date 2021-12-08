package com.ibm.aiops;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Resource {
    private String name;
    private String hostname;
    private String type;
    private String ipaddress;
    private String location;
}
