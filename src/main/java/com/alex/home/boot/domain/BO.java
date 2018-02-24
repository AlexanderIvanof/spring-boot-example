package com.alex.home.boot.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Business object example.
 *
 * @author Oleksandr Ivanov
 */
@Data
@Builder
public class BO implements Serializable {

    private String firstName;
    private String lastName;
    private UUID id;

}
