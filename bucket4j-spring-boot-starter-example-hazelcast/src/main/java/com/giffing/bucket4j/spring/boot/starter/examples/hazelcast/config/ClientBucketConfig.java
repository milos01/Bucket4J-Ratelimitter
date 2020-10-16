package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "buckets")
public class ClientBucketConfig {
    private SingleBucketConfig doSomeWork;
    private SingleBucketConfig doSomeOtherWork;

    @Data
    public static class SingleBucketConfig {
        private String name;
    }
}
