package br.edu.ifpe.register.register.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class ResponseCourseDTO {
    private UUID id;
    private String name;
    private String acronym;
    private Integer duration;
}
