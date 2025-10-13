package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.entity.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock
    private User user;

    @InjectMocks
    private TokenService tokenService;

    @Value("${api.security.token.secret}")
    private String secret = "secretKeyForTestingPurposesOnly";

    @Test
    void extractShouldReturnSubjectFromValidToken() {
        var id = UUID.randomUUID().toString();
        String token = "Bearer " + JWT.create()
                .withIssuer("api-local")
                .withSubject(id)
                .withClaim("role", Role.STUDENT.name())
                .withClaim("name", "student ifpe")
                .withClaim("email", "student@ifpe.edu.com.br")
                .withClaim("id", id)
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256(secret));

        String subject = TokenService.extract(token);

        assertEquals(id, subject);
    }

    @Test
    void extractShouldThrowExceptionWhenTokenIsMalformed() {
        String token = "MalformedToken";

        assertThrows(RuntimeException.class, () -> TokenService.extract(token));
    }

    @Test
    void extractIDShouldReturnSubjectFromAuthHeader() {
        var id = UUID.randomUUID().toString();
        String token = "Bearer " + JWT.create()
                .withIssuer("api-local")
                .withSubject(id)
                .withClaim("role", Role.ADMIN.name())
                .withClaim("name", "admin ifpe")
                .withClaim("email", "admin@ifpe.edu.com.br")
                .withClaim("id", id)
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256(secret));

        String subject = tokenService.extractID(token);

        assertEquals(id, subject);
    }

    @Test
    void extractIDShouldThrowExceptionWhenAuthHeaderIsMalformed() {
        String authHeader = "MalformedHeader";

        assertThrows(RuntimeException.class, () -> tokenService.extractID(authHeader));
    }
}
