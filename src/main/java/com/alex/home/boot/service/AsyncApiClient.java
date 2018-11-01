package com.alex.home.boot.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class AsyncApiClient {

    @Async
    public CompletableFuture<String> getResult() {
        log.debug("In result method");
        return CompletableFuture.completedFuture(heavyCommand());
    }

    @Async
    public CompletableFuture<String> getResultWithSupply() {
        log.debug("In supply async");
        return CompletableFuture.supplyAsync(this::heavyCommand);
    }

    @SneakyThrows
    private String heavyCommand() {
        int nextInt = ThreadLocalRandom.current().nextInt(1000, 3000);
        Thread.currentThread().sleep(nextInt);
        return "Done in " + nextInt;
    }

    @Async
    public CompletableFuture<String> getResultWithException() {
        return CompletableFuture.completedFuture(withException());
    }

    private String withException() {
        throw new RuntimeException("Not so fast");
    }
}
