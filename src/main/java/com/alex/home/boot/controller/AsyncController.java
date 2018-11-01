package com.alex.home.boot.controller;

import com.alex.home.boot.service.AsyncApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;

/**
 * @author Oleksandr Ivanov
 * @since
 */
@Controller
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncApiClient client;

    @RequestMapping("/async")
    @ResponseBody
    public String runAsync() throws Exception {
        log.debug("Start run Async");
        CompletableFuture<String> result1 = client.getResult();
        CompletableFuture<String> result2 = client.getResult();
        CompletableFuture.allOf(result1, result2).join();

        log.debug("Both results {}, {}", result1.get(), result2.get());
        return "Done";
    }

    @RequestMapping("/async-supply")
    @ResponseBody
    public String runAsyncSupply() throws Exception {
        log.debug("Start run Async supply");
        CompletableFuture<String> result1 = client.getResultWithSupply();
        CompletableFuture<String> result2 = client.getResultWithSupply();
        CompletableFuture.allOf(result1, result2).join();

        log.debug("Both results supply {}, {}", result1.get(), result2.get());
        return "Done";
    }

    @RequestMapping("/async-exception")
    @ResponseBody
    public String runAsyncException() throws Exception {
        log.debug("Start run Async Exception");
        CompletableFuture<String> result1 = client.getResultWithSupply();
        CompletableFuture<String> result2 = client.getResultWithException().exceptionally(ex -> {
            log.warn("Got exception. Default value will be returned.", ex);
            return "DEFAULT";
        });
        CompletableFuture.allOf(result1, result2).join();

        log.debug("Both results supply {}, {}", result1.get(), result2.get());
        return "Done";
    }
}
