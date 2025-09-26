package br.edu.ifpe.register.register.controller;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.dto.ResponseCreateUserDTO;
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
import java.util.UUID;
import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(
        summary = "Gets all users",
        description = "Endpoint responsible for getting all users",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content = @Content(schema = @Schema(implementation = CreateUserDTO.class, type = "application/json"))
                )
                
        }
    )
    @GetMapping("")
        public ResponseEntity<List<ResponseCreateUserDTO>> getAllUsers() {
                final var users = this.userService.getAllUsers();
                return ResponseEntity.ok(users);
        }
        @Operation(
            summary = "Get a user by its id",
            description = "Endpoint responsible for getting a user by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(schema = @Schema(implementation = CreateUserDTO.class, type = "application/json"))
                    ),
                    @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content = @Content(schema = @Schema(hidden = true))
                    )
            }
        )
        @GetMapping("/{id}")
        public ResponseEntity<ResponseCreateUserDTO> getUserById(@PathVariable final UUID id) {
                final var user = this.userService.getUserById(id);
                return ResponseEntity.ok(user);
        }
        @Operation(
                summary = "Updates a user by its id",
                description = "Endpoint responsible for updating a user by its id",
                responses = {
                        @ApiResponse(
                                responseCode = "204",
                                description = "No Content",
                                content = @Content(schema = @Schema(hidden = true))
                        ),
                        @ApiResponse(
                                responseCode = "404",
                                description = "Not Found",
                                content = @Content(schema = @Schema(hidden = true))
                        )
                }
        )
        @PutMapping("/{id}")
        public ResponseEntity<Void> updateUser(@PathVariable final UUID id, @Valid @RequestBody CreateUserDTO user) {
                this.userService.updateUser(id, user);
                return ResponseEntity.noContent().build();
        }
        @Operation(
                summary = "Deletes a user by its id",
                description = "Endpoint responsible for deleting a user by its id",
                responses = {
                        @ApiResponse(
                                responseCode = "204",
                                description = "No Content",
                                content = @Content(schema = @Schema(hidden = true))
                        ),
                        @ApiResponse(
                                responseCode = "404",
                                description = "Not Found",
                                content = @Content(schema = @Schema(hidden = true))
                        )
                }
        )
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable final UUID id) {
                this.userService.deleteUser(id);
                return ResponseEntity.noContent().build();
        }




    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Endpoint to receive CSV file and process it

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
