package com.concrete.teste.service;

import com.concrete.teste.Encrypt;
import com.concrete.teste.exceptions.EmailJaCadastradoException;
import com.concrete.teste.model.Phone;
import com.concrete.teste.model.User;
import com.concrete.teste.model.UserRequest;
import com.concrete.teste.model.UserResponse;
import com.concrete.teste.repository.PhoneRepository;
import com.concrete.teste.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    Encrypt encrypt;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    public UserResponse createUser(UserRequest userRequest) throws EmailJaCadastradoException {

        User user = new User();
        BeanUtils.copyProperties(userRequest, user);

        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException(HttpStatus.UNAUTHORIZED);
        }

        user.setPassword(encrypt.getPasswordEncrypted(userRequest.getPassword()));
        user.setCreated(LocalDate.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID());

        User userSaved = userRepository.save(user);

        if (user.getPhones() != null && !user.getPhones().isEmpty()) {
            for (Phone phone : user.getPhones()) {
                phone.setUser(userSaved);
            }
            phoneRepository.saveAll(user.getPhones());
        }

        UserResponse userResponse = new UserResponse();
        //BeanUtils.copyProperties(userSaved, userResponse);

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setPhones(user.getPhones());
        userResponse.setCreated(user.getCreated());
        userResponse.setModified(user.getModified());
        userResponse.setLastLogin(user.getLastLogin());
        userResponse.setToken(user.getToken());

        return userResponse;
    }
}