package com.cap.pocvng.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Class that obtains environment variables from application.properties starting with prefix cap.
 */
@Configuration
@ConfigurationProperties(prefix = "cap")
@Getter
@Setter
public class ConfigProperties {

    private int maxAmountResponse;
}
