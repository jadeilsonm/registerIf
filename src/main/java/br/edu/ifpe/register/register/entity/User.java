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
    public UUID id;
    @Column(unique = true, length = 20)
    public String registration;
    @Column(length = 150)
    public String name;
    @Column(unique = true, length = 150)
    public String email;
    public String password;
    @Enumerated(EnumType.STRING)
    public Role role;
}
