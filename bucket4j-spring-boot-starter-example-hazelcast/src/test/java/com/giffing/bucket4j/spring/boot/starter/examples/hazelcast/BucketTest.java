//package com.giffing.bucket4j.spring.boot.starter.examples.hazelcast;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//import java.util.stream.IntStream;
//
//import static org.assertj.core.api.Assertions.fail;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("servlet")
//public class BucketTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void helloTest() throws Exception {
//        String url = "/";
//        IntStream.rangeClosed(1, 5)
//                .boxed()
//                .sorted(Collections.reverseOrder())
//                .forEach(counter -> {
//                    successfulWebRequest(url, counter - 1);
//                });
//
//        blockedWebRequestDueToRateLimit(url);
//    }
//
//    private void successfulWebRequest(String url, Integer remainingTries) {
//        try {
//            this.mockMvc
//                    .perform(get(url))
//                    .andExpect(status().isOk())
//                    .andExpect(header().longValue("X-Rate-Limit-Remaining", remainingTries))
//                    .andExpect(content().string(containsString("")));
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail(e.getMessage());
//        }
//    }
//
//    private void blockedWebRequestDueToRateLimit(String url) throws Exception {
//        this.mockMvc
//                .perform(get(url))
//                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()))
//                .andExpect(content().string(containsString("{ \"message\": \"Too many requests!\" }")));
//    }
//}
