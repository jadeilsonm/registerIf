package br.edu.ifpe.register.register.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @Size(min = 8, max = 150, message = "The password must be between 8 and 150 characters long.")
    private String email;
    @Size(min = 8, max = 250, message = "The password must be between 8 and 150 characters long.")
    private String password;
}
