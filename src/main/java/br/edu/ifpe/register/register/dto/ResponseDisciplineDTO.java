package br.edu.ifpe.register.register.dto;


import lombok.Getter;
import java.util.UUID;

@Getter

public class ResponseDisciplineDTO {
    private UUID id;
    private UUID courseId;
    private String name;
    private Integer workload;
}
