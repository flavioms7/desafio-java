package com.concrete.teste.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDate dateOfBirth;
}
