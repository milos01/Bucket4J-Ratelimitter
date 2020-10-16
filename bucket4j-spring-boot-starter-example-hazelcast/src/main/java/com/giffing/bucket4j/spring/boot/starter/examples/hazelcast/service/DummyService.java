package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.service;

import com.melita.commons.ratelimiter.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class DummyService {

    private final RateLimiterService rateLimiterService;

//    @WaitForMyTurn(bucketName = "#{@clientBucketConfig.getDoSomeWork().getName()}")
    @Async
    public CompletableFuture<DummyGateway.RateLimiterResponse> doSomeWork() {
        try {
            int randomInterval = new Random().nextInt(2 + 1)  + 3;
            log.info("Method execution time: {}", randomInterval);
            Thread.sleep(randomInterval * 1000);
//            return DummyGateway.RateLimiterResponse.builder().id(1).build();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rateLimiterService.releaseThread("bucket1");
        }
        return CompletableFuture.completedFuture(DummyGateway.RateLimiterResponse.builder().id(1).build());
    }

//    @WaitForMyTurn(bucketName = "#{@clientBucketConfig.getDoSomeOtherWork().getName()}")
    public DummyGateway.RateLimiterResponse doSomeOtherWork() {
        try {
            int randomInterval = new Random().nextInt(2 + 1)  + 4;
            log.info("Method execution time: {}", randomInterval);
            Thread.sleep(randomInterval * 1000);
            return DummyGateway.RateLimiterResponse.builder().id(2).build();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
