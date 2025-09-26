package br.edu.ifpe.register.register.dto;


import lombok.Getter;
import java.util.UUID;

@Getter
public class ResponseCourseDTO {
    private UUID id;
    private String name;
    private String acronym;
    private Integer duration;
}
