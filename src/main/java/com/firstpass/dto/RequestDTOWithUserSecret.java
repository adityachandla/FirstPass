package com.firstpass.dto;

import lombok.Data;

@Data
public class RequestDTOWithUserSecret {
    String version;
    String userSecret;
    String appName;
}
