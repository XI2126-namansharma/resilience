package com.poc.resilience.service.impl;

import com.poc.resilience.service.ResilienceService;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResilienceServiceImpl implements ResilienceService {

    private final RetryRegistry retryRegistry;

    @Override
    public String retry(String product) {
        Retry retry = retryRegistry.retry("getPrice");
        String apply = Retry.decorateFunction(retry, this::getProductPrice).apply(product);
        log.info(apply);
        return apply;
    }

    private String getProductPrice(String product) {
        SecureRandom r = new SecureRandom();
        int chance = r.nextInt(100);
        if (chance < 70) {
            System.out.println(" Service down");
            throw new RuntimeException("Service not available");
        }
        System.out.println("Service up");
        return "Price of " + product + " is $" + chance;
    }
}
