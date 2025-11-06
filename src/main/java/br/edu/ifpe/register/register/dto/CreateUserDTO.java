package br.edu.ifpe.register.register.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    @Schema(description = "Name is required", example = "João de deus")
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 150, message = "The name must be between 3 and 150 characters long.")
    private String name;
    @Email(message = "Email is required")
    @NotBlank(message = "email is required")
    @Schema(description = "unique email per user", example = "joao@email.com")
    @Size(max = 150, message = "The email must be between 1 and 150 characters long.")
    private String email;
    @Schema(description = "unique registration per user", example = "2022ADSPM0123")
    @Size(max = 20, message = "The name registration be between 1 and 20 characters long.")
    private String registration;
    @Schema(description = "phone of user", example = "(81)99999-9999")
    @Size(max = 15, message = "The phone must be between 1 and 15 characters long.")
    private String phone;
    @Schema(description = "password fo user, nullable = false", example = "password")
    private String password;
    @Pattern(regexp = "ADMIN|STUDENT|PROFESSOR|SECRETARY|TECHNICIAN|OTHER", message = "Invalid role")
    private String role;
}
