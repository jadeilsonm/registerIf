package br.edu.ifpe.register.register.controller;

import br.edu.ifpe.register.register.dto.DisciplineDTO;
import br.edu.ifpe.register.register.service.DisciplineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;  

@RestController
@RequestMapping("/discipline")
@Tag(name = "Discipline management", description = "API to manage discipline")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @Operation(
            summary = "Creates a new discipline",
            description = "Endpoint responsible for adding a new Discipline",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<Void> insertCourse(@Valid @RequestBody DisciplineDTO discipline) {
        this.disciplineService.insertDiscipline(discipline);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(
        summary = "Gets all disciplines",
        description = "Endpoint responsible for getting all disciplines",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Ok",
                        content = @Content(schema = @Schema(implementation = DisciplineDTO.class, type = "application/json"))
                )
        }
    )
    @GetMapping("")
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplines() {
        final var disciplines = this.disciplineService.getAllDisciplines();
        return ResponseEntity.ok(disciplines).build();
    }
    @Operation(
            summary = "Get a discipline by its id",
            description = "Endpoint responsible for getting a discipline by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(schema = @Schema(implementation = DisciplineDTO.class, type = "application/json"))
                    )
                    @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> getDisciplineById(@PathVariable final UUID id) {
        final var discipline = this.disciplineService.getDisciplineById(id);
        return ResponseEntity.ok(discipline);
    }
    @Operation(
            summary = "Updates a discipline by its id",
            description = "Endpoint responsible for updating a discipline by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content(schema = @Schema(hidden = true))
                    )
                    @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDiscipline(@PathVariable final UUID id, @Valid @RequestBody DisciplineDTO discipline) {
        this.disciplineService.updateDiscipline(id, discipline);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Deletes a discipline by its id",
            description = "Endpoint responsible for deleting a discipline by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content(schema = @Schema(hidden = true))
                    )
                    @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable final UUID id) {
        this.disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}
