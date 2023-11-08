package com.dubrouskaya.cloud.controller;


import com.dubrouskaya.jmpdto.dto.request.UserRequestDto;
import com.dubrouskaya.jmpdto.dto.response.UserResponseDto;
import com.dubrouskaya.jmpserviceapi.UserService;
import com.dubrouskaya.jmpservicerest.controller.UserController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private static final String USERS_REL = "users";
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Override
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.createUser(userRequestDto);
        user.add(
                linkTo(methodOn(UserControllerImpl.class).createUser(userRequestDto)).withSelfRel(),
                linkTo(methodOn(UserControllerImpl.class).updateUser(userRequestDto)).withRel("update"),
                linkTo(methodOn(UserControllerImpl.class).getUser(user.getId())).withRel("user"),
                linkTo(methodOn(UserControllerImpl.class).getAllUsers()).withRel(USERS_REL)
        );
        return user;
    }

    @PutMapping
    @Override
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.updateUser(userRequestDto);
        user.add(
                linkTo(methodOn(UserControllerImpl.class).updateUser(userRequestDto)).withSelfRel(),
                linkTo(methodOn(UserControllerImpl.class).createUser(userRequestDto)).withRel("create"),
                linkTo(methodOn(UserControllerImpl.class).getUser(user.getId())).withRel("user"),
                linkTo(methodOn(UserControllerImpl.class).getAllUsers()).withRel(USERS_REL)
        );
        return user;
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteUser(@Parameter(name = "id") @PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @Override
    public UserResponseDto getUser(@Parameter(name = "id") @PathVariable Long id) {
        UserResponseDto user = userService.getUser(id);
        user.add(
                linkTo(methodOn(UserControllerImpl.class).getAllUsers()).withRel(USERS_REL),
                linkTo(methodOn(UserControllerImpl.class).getUser(id)).withSelfRel());
        return user;
    }

    @GetMapping
    @Override
    public CollectionModel<UserResponseDto> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        users.forEach(user -> user.add(linkTo(methodOn(UserControllerImpl.class).getUser(user.getId())).withSelfRel()));
        Link allUsersLink = linkTo(methodOn(UserControllerImpl.class).getAllUsers()).withSelfRel();
        return CollectionModel.of(users, allUsersLink);
    }
}
