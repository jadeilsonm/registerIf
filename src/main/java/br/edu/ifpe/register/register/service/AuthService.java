package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.LoginDTO;
import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.repository.UserRepository;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(final UserRepository userRepository,
                       final TokenService tokenService,
                       final BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginDTO loginDTO) throws AuthException {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AuthException("Invalid email or password"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid email or password");
        }

        return tokenService.generateToken(user);
    }
}