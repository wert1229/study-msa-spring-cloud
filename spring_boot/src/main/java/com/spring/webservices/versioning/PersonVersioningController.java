package com.spring.webservices.versioning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 urlV1() {
        return new PersonV1("Bob Char");
    }

    @GetMapping("v2/person")
    public PersonV2 urlV2() {
        return new PersonV2(new Name("Bob", "Char"));
    }

    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Bob Char");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Bob", "Char"));
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Bob Char");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Bob", "Char"));
    }

    @GetMapping(value = "person/produce", produces = "application/vnd.app-v1+json")
    public PersonV1 produceV1() {
        return new PersonV1("Bob Char");
    }

    @GetMapping(value = "person/produce", produces = "application/vnd.app-v2+json")
    public PersonV2 produceV2() {
        return new PersonV2(new Name("Bob", "Char"));
    }
}
