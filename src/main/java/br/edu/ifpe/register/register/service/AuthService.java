package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.LoginDTO;
import br.edu.ifpe.register.register.dto.ResponseLoginDTO;
import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
//    private final TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseLoginDTO Login (LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.)
                .orElseThrow(() -> new AuthException("Invalid email or password"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new AuthException("Invalid email or password");
        }

        return tokenService.generateToken(user);
    }
}