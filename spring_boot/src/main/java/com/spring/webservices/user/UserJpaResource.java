package com.spring.webservices.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserJpaResource {

    private final UserDaoService service;
    private final UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<EntityModel<User>> retrieveAllUsers() {
        List<User> users = userRepository.findAll();

        List<EntityModel<User>> list = new ArrayList<>();

        for (User user : users) {
            list.add(EntityModel.of(user).add(
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveUser(user.getId()))
                            .withSelfRel()));
        }
        return list;
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id-" + id);

        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
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
