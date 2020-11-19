package com.spring.webservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;
}
