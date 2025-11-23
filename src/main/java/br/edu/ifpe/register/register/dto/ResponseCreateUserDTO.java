package br.edu.ifpe.register.register.dto;

import java.util.UUID;


import br.edu.ifpe.register.register.entity.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class ResponseCreateUserDTO {
    private UUID id;
    private String name;
    private String email;
    private Role role;
    private String registration;
}
