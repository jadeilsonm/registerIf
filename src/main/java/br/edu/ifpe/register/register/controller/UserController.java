package br.edu.ifpe.register.register.controller;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/register")
@Tag(name = "User management", description = "API to manage users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Creates a new user and sends it to the other services",
            description = "Endpoint responsible for adding a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<Void> insertUser(
            @Valid @RequestBody CreateUserDTO user
    ) {
        userService.userRegister(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Create new users through the csv file and send it to other services",
            description = "Endpoint responsible for adding a new users",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping(path = "/user/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> insertUserWithCsv(
            @RequestParam("csv") MultipartFile csv
    ) {
        userService.userRegisterByFile(csv);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
