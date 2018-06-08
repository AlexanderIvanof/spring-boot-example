package com.alex.home.boot.controller;

import com.alex.home.boot.domain.BO;
import com.alex.home.boot.service.BusinessService;
import com.alex.home.boot.service.EncryptionService;
import com.alex.home.logger.annotation.LogTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@Slf4j
public class SimpleController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private EncryptionService encryptionService;

    @RequestMapping("/")
    public String index() {
        return "Spring boot is here!";
    }

    @RequestMapping(value = "/count/{length}", method = RequestMethod.GET)
    @LogTime
    public String taskToMeasure(@PathVariable("length") Long length) {
        long result = 0L;
        for (int i = 0; i < length.intValue(); i++) {
            result += i;
        }
        return String.format("Result is %d", result);
    }

    @RequestMapping(value = "/decode/{text}", method = RequestMethod.GET)
    public String testDecodeString(@PathVariable("text") String text) {
        String result;
        try {
            result = encryptionService.decrypt(text);
        } catch (Exception e) {
            result = text;
            log.error("Error during decoding", e);
        }
        return String.format("Result is |%s|", result);
    }

    @RequestMapping(value = "/encode/{text}", method = RequestMethod.GET)
    public String testEncodeString(@PathVariable("text") String text) {
        String result;
        try {
            result = encryptionService.encrypt(text);
        } catch (Exception e) {
            result = text;
            log.error("Error during encoding", e);
        }
        return String.format("Result is |%s|, with length %d, for input string length %d", result, result.length(), text.length());
    }

    @RequestMapping("/masked")
    public String triggerMaskedLogging() {
        BO businessObject = BO.builder().firstName("Sherlock").lastName("Holmes").id(UUID.randomUUID()).build();
        businessService.setPaymentForPerson(businessObject, BigDecimal.valueOf(123.22));
        return "Triggered";
    }

}