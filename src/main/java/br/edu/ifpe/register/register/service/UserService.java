package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.dto.ResponseCreateUserDTO;
import br.edu.ifpe.register.register.dto.UserCsvDTO;
import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.mapper.UserMapper;
import br.edu.ifpe.register.register.repository.UserRepository;
import br.edu.ifpe.register.register.service.rabbit.RegisterSend;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final RegisterSend registerSend;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(final RegisterSend registerSend,
                       final UserRepository userRepository,
                       final UserMapper userMapper,
                       final BCryptPasswordEncoder passwordEncoder) {
        this.registerSend = registerSend;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void userRegister(final CreateUserDTO user) {
        final User newUser = userMapper.toEntity(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        this.processingUser(newUser);
    }
    public List<ResponseCreateUserDTO> getAllUsers(){
        return this.userRepository.findAll().stream().map(userMapper::toResponseCreateUserDTO).collect(Collectors.toList());
    }
    public ResponseCreateUserDTO getUserById(final UUID id) {
        return this.userRepository.findById(id).map(userMapper::toResponseCreateUserDTO).orElseThrow();
    }
    public void updateUser(final UUID id, final CreateUserDTO user) {
        final var existingUser = userRepository.findById(id).orElseThrow();
        userMapper.updateEntity(user, existingUser);
        this.processingUser(existingUser);
    }
    public void deleteUser(final UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found id: " + id);
        }
        userRepository.deleteById(id);
    }
    public void userRegisterByFile(final MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<UserCsvDTO> csvToBean = new CsvToBeanBuilder<UserCsvDTO>(reader)
                    .withType(UserCsvDTO.class)
                    .build();
            var users = csvToBean.parse();
            users.forEach(user -> {
                var newUser = userMapper.ToEntityByUserCsvDTO(user);
                this.processingUser(newUser);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Failed to processing", ex);
        }
    }

    private void processingUser(final User user) {
        try {
            userRepository.save(user);
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
