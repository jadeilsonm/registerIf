package br.edu.ifpe.register.register.configurations;

import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.entity.enums.Role;
import br.edu.ifpe.register.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.repository.findById(UUID.fromString(name)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var roles = new ArrayList<Role>();
        roles.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roles.stream().map(Role::name).map(org.springframework.security.core.authority.SimpleGrantedAuthority::new).toList()
        );
    }
}