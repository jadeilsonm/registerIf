package br.edu.ifpe.register.register.entity;

import br.edu.ifpe.register.register.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, length = 20)
    private String registration;
    @Column(length = 150)
    private String name;
    @Column(unique = true, length = 150)
    private String email;
    private String password;
    @Column(length = 15)
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean isActive;
}
