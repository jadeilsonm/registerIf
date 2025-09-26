package br.edu.ifpe.register.register.dto;

import java.util.UUID;


import lombok.Getter;


@Getter
public class ResponseCreateUserDTO {
    private UUID id;
    private String name;
    String email;
    String registration;
}
