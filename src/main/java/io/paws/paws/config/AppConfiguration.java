package io.paws.paws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
