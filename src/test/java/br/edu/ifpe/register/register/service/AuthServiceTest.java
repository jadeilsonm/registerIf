package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.LoginDTO;
import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.repository.UserRepository;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void loginDeveRetornarTokenQuandoCredenciaisCorretas() throws AuthException {
        LoginDTO loginDTO = new LoginDTO("email@email.com", "senha123");
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("senhaCodificada");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("token123");

        String token = authService.login(loginDTO);

        assertEquals("token123", token);
    }

    @Test
    void loginDeveLancarExcecaoQuandoEmailNaoEncontrado() {
        LoginDTO loginDTO = new LoginDTO("email@email.com", "senha123");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());

        assertThrows(AuthException.class, () -> authService.login(loginDTO));
    }

    @Test
    void loginDeveLancarExcecaoQuandoSenhaIncorreta() {
        LoginDTO loginDTO = new LoginDTO("email@email.com", "senha123");
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("senhaCodificada");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(AuthException.class, () -> authService.login(loginDTO));
    }
}
