package com.poc.resilience.controller;

import com.poc.resilience.service.ResilienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResilienceResource {

    @Autowired
    ResilienceService resilienceService;

    @GetMapping("/retry/{product}")
    public String resilienceRetryCheck(@PathVariable String product) {
        return resilienceService.retry(product);
    }
}
