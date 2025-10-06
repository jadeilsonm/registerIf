package br.edu.ifpe.register.register.repository;

import br.edu.ifpe.register.register.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String Email);
}
