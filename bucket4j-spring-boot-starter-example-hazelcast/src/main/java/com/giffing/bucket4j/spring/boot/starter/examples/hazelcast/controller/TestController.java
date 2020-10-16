package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.controller;

import com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.service.DummyGateway;
import com.giffing.bucket4j.spring.boot.starter.examples.hazelcast.service.DummyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class TestController {

	private final DummyGateway dummyGateway;

	@GetMapping("hello")
	public DummyGateway.RateLimiterResponse hello() {
		return dummyGateway.rateLimiter(DummyGateway.RateLimiterRequest.builder().build());
	}

	@GetMapping("helloAgain")
	public DummyGateway.RateLimiterResponse helloAgain() {
		return dummyGateway.otherRateLimiter(DummyGateway.RateLimiterRequest.builder().build());
	}
	
	
	
	
}
