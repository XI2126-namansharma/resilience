package com.poc.resilience.predicate;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Slf4j
public class ResiliencePredicate implements Predicate<String> {

    @Override
    public boolean test(String str) {
        return false;
    }
}
