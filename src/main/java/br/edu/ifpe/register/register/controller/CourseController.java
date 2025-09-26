package br.edu.ifpe.register.register.controller;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.dto.ResponseCourseDTO;
import br.edu.ifpe.register.register.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/course")
@Tag(name = "Course management", description = "API to manage course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @Operation(
            summary = "Creates a new course",
            description = "Endpoint responsible for adding a new course",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<Void> insertCourse(@Valid @RequestBody CourseDTO course) {
        this.courseService.insertCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }
    @Operation(
            summary = "Get all courses",
            description = "Endpoint responsible for getting all courses",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(schema = @Schema(implementation = CourseDTO.class, type = "application/json"))
                    ),
                        @ApiResponse(
                                responseCode = "404",
                                description = "Not Found",
                                content = @Content(schema = @Schema(hidden = true))
                        ),
            }
    )

    @GetMapping("")
    public ResponseEntity<List<ResponseCourseDTO>> getAllCourses() {
        final var courses = this.courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @Operation( 
            summary = "Get a course by its id",
            description = "Endpoint responsible for getting a course by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(schema = @Schema(implementation = CourseDTO.class, type = "application/json"))
                    ),
                    @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCourseDTO> getCourseById(@PathVariable final UUID id) {
        final var course = this.courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    @Operation(
            summary = "Updates a course by its id",
            description = "Endpoint responsible for updating a course by its id",
            responses = {
                    @ApiResponse (
                            responseCode = "200",
                            description = "Ok",
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
    public ResponseEntity<Void> updateCourse(@PathVariable final UUID id, @Valid @RequestBody CourseDTO course) {
        this.courseService.updateCourse(id, course);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Deletes a course by its id",
            description = "Endpoint responsible for deleting a course by its id",
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
    public ResponseEntity<Void> deleteCourse(@PathVariable final UUID id) {
        this.courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    } 
}