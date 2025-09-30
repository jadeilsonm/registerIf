package br.edu.ifpe.register.register.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class ResponseDisciplineDTO {
    private UUID id;
    private UUID courseId;
    private String name;
    private Integer workload;
}
