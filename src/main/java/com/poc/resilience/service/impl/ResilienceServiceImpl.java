package com.poc.resilience.service.impl;

import com.poc.resilience.service.ResilienceService;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.function.Function;

@Slf4j
@Service
public class ResilienceServiceImpl implements ResilienceService {

    @Override
    public String retry(String product) {

        RetryConfig config = RetryConfig.custom()
                .maxAttempts(10)
                .waitDuration(Duration.ofMillis(500))
                .failAfterMaxAttempts(true)
                .build();
        RetryRegistry retryRegistry = RetryRegistry.of(config);
        Retry retry = retryRegistry.retry("gettingPriceFor" + product);

        retry.getEventPublisher().onRetry(e -> log.info("Retry attempt " + e.getNumberOfRetryAttempts() + " for " + e.getName()));

        Function<String, String> decorated = Retry.decorateFunction(retry, (String s) -> this.getProductPrice(product));

        String apply = decorated.apply(product);
        log.info(apply);
        return apply;
    }

    private String getProductPrice(String product) {
        SecureRandom r = new SecureRandom();
        int chance = r.nextInt(100);
        if (chance < 50) {
            System.out.println(" Service down");
            throw new RuntimeException("Service not available");
        }
        System.out.println("Service up");
        return "Price of " + product + " is $" + chance;
    }
}
