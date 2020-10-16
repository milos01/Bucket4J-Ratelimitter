package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.service;

import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
public class ConcurrencyService {
    public void throttle() {

    }
}
