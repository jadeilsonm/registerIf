package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.mapper.UserMapper;
import br.edu.ifpe.register.register.repository.UserRepository;
import br.edu.ifpe.register.register.service.rabbit.RegisterSend;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    private final RegisterSend registerSend;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(final RegisterSend registerSend,
                       final UserRepository userRepository,
                       final UserMapper userMapper) {
        this.registerSend = registerSend;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void userRegister(final CreateUserDTO user) {
        final User newUser = userMapper.toEntity(user);
        try {
            userRepository.save(newUser);
            registerSend.send(user);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(UserService.class.getName())
                    .log(Level.SEVERE,
                            "Could not save entity due to duplicate or invalid data.",
                            ex);
            throw ex;
        } catch (AmqpConnectException ex) {
            Logger.getLogger(UserService.class.getName())
                    .log(Level.SEVERE,
                            "Failed to send message ",
                            ex);
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName())
                    .log(Level.SEVERE, "Failed to send message ", ex);
            throw new RuntimeException(ex);
        }
    }
}
