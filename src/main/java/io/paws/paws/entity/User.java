package io.paws.paws.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private int tel;
    private String name;
    private String email;
    private String password;
}
