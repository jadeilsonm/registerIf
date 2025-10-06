package br.edu.ifpe.register.register.controller;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.dto.LoginDTO;
import br.edu.ifpe.register.register.entity.User;
import br.edu.ifpe.register.register.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "" ,description = "")
public class AuthController {



    @PostMapping("/login")
    public ResponseEntity<Void> insertUser(@Valid @RequestBody LoginDTO loginDTO) {

//        loginDTO.
//        userService.userRegister(user);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
