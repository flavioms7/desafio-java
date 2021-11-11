package com.concrete.teste.controller;

import com.concrete.teste.model.LoginRequest;
import com.concrete.teste.model.UserRequest;
import com.concrete.teste.model.UserResponse;
import com.concrete.teste.service.LoginService;
import com.concrete.teste.service.ProfileService;
import com.concrete.teste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    ProfileService profileService;

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

    @PostMapping("/profile")
    public ResponseEntity<UserResponse> profile(@RequestHeader String token, @RequestParam String id) {

        UserResponse userDTO = profileService.profile(token, id);

        return ResponseEntity.ok().body(userDTO);
    }
}
