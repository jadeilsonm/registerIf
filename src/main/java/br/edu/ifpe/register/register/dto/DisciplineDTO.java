package br.edu.ifpe.register.register.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineDTO {
    @NotNull
    private UUID courseId;
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private Integer workload;
}
