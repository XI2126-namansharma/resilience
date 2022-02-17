package com.poc.resilience.controller;

import com.poc.resilience.service.ResilienceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ResilienceResource.class)
class ResilienceResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ResilienceService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void resilienceRetryCheck() throws Exception {
        when(service.retry(any())).thenReturn("price of product is $80");
        mockMvc.perform(get("/retry/apple")).andExpect(status().isOk());

    }
}