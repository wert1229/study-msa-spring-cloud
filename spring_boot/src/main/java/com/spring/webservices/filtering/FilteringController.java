package com.spring.webservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue someBean() {
        SomeBean bean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue value = new MappingJacksonValue(someBean());

        value.setFilters(filterProvider);

        return value;
    }
}
