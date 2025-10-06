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
public class ResponseDisciplineDTO {
    private UUID id;
    private UUID courseId;
    private String name;
    private Integer workload;
}
