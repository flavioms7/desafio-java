package com.concrete.teste.controller;

import com.concrete.teste.model.LoginRequest;
import com.concrete.teste.model.UserRequest;
import com.concrete.teste.model.UserResponse;
import com.concrete.teste.service.LoginService;
import com.concrete.teste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){

        UserResponse response = userService.createUser(userRequest);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {

        UserResponse response = loginService.login(loginRequest);

        return ResponseEntity.ok().body(response);
    }
}
