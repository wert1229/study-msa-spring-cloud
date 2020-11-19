package com.spring.webservices.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserDaoService service;

    @GetMapping("/users")
    public List<EntityModel<User>> retrieveAllUsers() {
        List<User> users = service.findAll();

        List<EntityModel<User>> list = new ArrayList<>();

        for (User user : users) {
            list.add(EntityModel.of(user).add(
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(user.getId()))
                            .withSelfRel()));
        }
        return list;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.fineOne(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);

        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
