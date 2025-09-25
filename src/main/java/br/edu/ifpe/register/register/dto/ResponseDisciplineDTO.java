package br.edu.ifpe.register.register.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseDisciplineDTO {
    @Null
    private UUID id;
    @NotNull
    private UUID courseId;
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private Integer workload;
}
