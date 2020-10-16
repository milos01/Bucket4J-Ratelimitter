package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@MessagingGateway
@Service
public interface DummyGateway {
    @Gateway(requestChannel = "dummyRequestChannel")
    RateLimiterResponse rateLimiter(RateLimiterRequest rateLimiterRequest);

    @Gateway(requestChannel = "otherDummyRequestChannel")
    RateLimiterResponse otherRateLimiter(RateLimiterRequest rateLimiterRequest);

    @Data
    @Builder
    @AllArgsConstructor
    class RateLimiterResponse implements Serializable {
        private static final long serialVersionUID = 1712773860636627014L;
        private int id;
    }

    @Data
    @Builder
    class RateLimiterRequest implements Serializable {
        private static final long serialVersionUID = 1732773860636627014L;
    }
}
