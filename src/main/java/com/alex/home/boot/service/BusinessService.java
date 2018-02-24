package com.alex.home.boot.service;

import com.alex.home.boot.domain.BO;
import com.alex.home.logger.annotation.LogMasked;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Business service for aspects testing needs.
 *
 * @author Oleksandr Ivanov
 */
@Service
@Slf4j
public class BusinessService {

    @LogMasked(mask = "##**##")
    public void setPaymentForPerson(BO account, BigDecimal amount) {
        log.info("Just for tests");
    }
}
