package com.concrete.teste.service;

import com.concrete.teste.exceptions.NaoAutorizadoException;
import com.concrete.teste.exceptions.SessaoInvalidaException;
import com.concrete.teste.model.ProfileRequest;
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
public class ProfileService {

    @Autowired
    UserRepository userRepository;

    public UserResponse profile(String token, String id) throws NaoAutorizadoException {

        Optional<User> userOptionalToken = userRepository.findByToken(UUID.fromString(token));
        userOptionalToken.orElseThrow(() -> new NaoAutorizadoException(HttpStatus.UNAUTHORIZED));

        Optional<User> userOptionalId = userRepository.findById(UUID.fromString(id));
        if(userOptionalId.isPresent()){

            UUID userToken = userOptionalId.get().getToken();
            UUID tokenProfile = UUID.fromString(token);

            if(!tokenProfile.equals(userToken)){
                throw new NaoAutorizadoException(HttpStatus.UNAUTHORIZED);
            }else if(LocalDateTime.now().minusMinutes(30).compareTo(userOptionalId.get().getLastLogin()) > 0){
                throw new SessaoInvalidaException(HttpStatus.GONE);
            }
        }else{
            throw new NaoAutorizadoException(HttpStatus.UNAUTHORIZED);
        }

        UserResponse userDTO = new UserResponse();
        BeanUtils.copyProperties(userOptionalId.get(), userDTO);

        return userDTO;
    }
}
