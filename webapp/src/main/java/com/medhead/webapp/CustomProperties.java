package com.medhead.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.medhead.webapp")
public class CustomProperties {

    private String apiUrl;

    private String apiGoogleMapsUrl;

    private String keyGoogleMaps;

    private String cleCryptagePass;
    
}
