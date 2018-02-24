package com.alex.home.boot.controller;

import com.alex.home.boot.domain.BO;
import com.alex.home.boot.service.BusinessService;
import com.alex.home.logger.annotation.LogTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class SimpleController {

    @Autowired
    private BusinessService businessService;

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

    @RequestMapping("/masked")
    public String triggerMaskedLogging() {
        BO businessObject = BO.builder().firstName("Sherlock").lastName("Holmes").id(UUID.randomUUID()).build();
        businessService.setPaymentForPerson(businessObject, BigDecimal.valueOf(123.22));
        return "Triggered";
    }

}