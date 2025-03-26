package com.smartjob.user_registration_api.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app.password")
public class PasswordConfigProperties {
    private final String regex;
    private final String regexMessage;

    public PasswordConfigProperties(String regex, String regexMessage) {
        this.regex = regex;
        this.regexMessage = regexMessage;
    }

}
