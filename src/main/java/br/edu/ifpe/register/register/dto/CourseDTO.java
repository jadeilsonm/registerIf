package br.edu.ifpe.register.register.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    @Null
    private java.util.UUID id;
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 150, message = "The name must be between 3 and 150 characters long.")
    private String name;
    private String acronym;
    @Min(1)
    @Max(12)
    private Integer duration;
}
