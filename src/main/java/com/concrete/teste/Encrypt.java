package com.concrete.teste;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Encrypt {

    public String getPasswordEncrypted(String password) {

        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public boolean passwordAuthentication(String passwordRequest, String userPassword){

        boolean isAuthenticationSuccessful = BCrypt.checkpw(passwordRequest, userPassword);

        return isAuthenticationSuccessful;
    }
}

