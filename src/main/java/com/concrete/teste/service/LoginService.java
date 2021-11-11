package com.concrete.teste.service;

import com.concrete.teste.Encrypt;
import com.concrete.teste.exceptions.UserAndPassInvalidException;
import com.concrete.teste.model.LoginRequest;
import com.concrete.teste.model.User;
import com.concrete.teste.model.UserResponse;
import com.concrete.teste.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    Encrypt encrypt;

    @Autowired
    UserRepository userRepository;

    public UserResponse login(LoginRequest loginRequest) throws UserAndPassInvalidException {

        Optional<User> userOptional = userRepository.findUserByEmail(loginRequest.getEmail());
        User user = userOptional.orElseThrow(() -> new UserAndPassInvalidException(HttpStatus.UNAUTHORIZED));

        if(!encrypt. passwordAuthentication(loginRequest.getPassword(), user.getPassword())){

            throw new UserAndPassInvalidException(HttpStatus.UNAUTHORIZED);
        }

        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID());

        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        return userResponse;
    }
}
