package com.concrete.teste.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    private String name;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDateTime lastLogin;
    private LocalDateTime modified;

    @Column(columnDefinition = "VARCHAR(255)")
    private UUID token;

    @OneToMany(mappedBy = "user")
    private List<Phone> phones;

    public User() {
        super();
    }
}