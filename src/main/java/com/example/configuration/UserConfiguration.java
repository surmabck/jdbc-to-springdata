package com.example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by surmab on 06.04.2017.
 */

@Configuration
@ConfigurationProperties(prefix = "configuration.user")
public class UserConfiguration {

    private String greeting;

    public UserConfiguration(){

    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
