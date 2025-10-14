package br.edu.ifpe.register.register.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCourseDTO {
    private UUID id;
    private String name;
    private String acronym;
    private Integer duration;
}
