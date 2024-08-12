package com.imran.security_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = @UniqueConstraint(
        name = "password_unique",columnNames = "password"
))
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
