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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
