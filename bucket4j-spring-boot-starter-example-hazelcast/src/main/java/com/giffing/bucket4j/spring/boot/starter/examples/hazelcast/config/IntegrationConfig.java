package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.config;

import com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.service.DummyService;
import com.melita.commons.ratelimiter.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.Channels;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannelSpec;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final DummyService dummyService;
    private final RateLimiterService rateLimiterService;
    private final ClientBucketConfig bucketConfig;

    @Bean
    IntegrationFlow dummyRequest() {
        return IntegrationFlows.from("dummyRequestChannel")
                .channel(this::buildQueueChannelSpec)
                .handle((p, h) -> rateLimiterService.waitForMyTurn(bucketConfig.getDoSomeWork().getName()))
                .handle((p, h) -> {
                    try {
                        return dummyService.doSomeWork().get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return p;
                })
//                .handle((p, h) -> rateLimiterService.releaseThread(bucketConfig.getDoSomeWork().getName()))
                .get();
    }

    @Bean
    IntegrationFlow otherDummyRequest() {
        return IntegrationFlows.from("otherDummyRequestChannel")
                .channel(this::buildQueueChannelSpec)
                .handle((p, h) -> dummyService.doSomeOtherWork())
                .get();
    }

    private MessageChannelSpec<?, ?> buildQueueChannelSpec(Channels c) {
        return c.executor(Executors.newFixedThreadPool(5));
    }
}
