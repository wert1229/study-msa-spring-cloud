package com.spring.webservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;

}
