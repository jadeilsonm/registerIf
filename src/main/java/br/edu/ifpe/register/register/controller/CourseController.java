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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<Void> insertCourse(@Valid @RequestBody CourseDTO course) {
        this.courseService.insertCourse(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all courses",
            description = "Retrieves a list of all courses",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of courses returned successfully"
                    )
            }
    )
    @GetMapping("")
    public ResponseEntity<List<ResponseCourseDTO>> getAllCourses(){
        List<ResponseCourseDTO> courses = this.courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @Operation(
            summary = "Get course by ID",
            description = "Retrieves a single course by its id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course Found"),
                    @ApiResponse(responseCode = "404", description = "Course not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCourseDTO> getCourseById(@PathVariable UUID id) {
        Optional<ResponseCourseDTO> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a course",
            description = "Updates an existing course by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Course not found")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable UUID id, @Valid @RequestBody CourseDTO courseDTO){
        Optional<CourseDTO> updatedCourse = courseService.updateCourse(id, courseDTO);
        return updatedCourse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a course",
            description = "Deletes a course by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Course not found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        boolean deleted = courseService.deleteCourse(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}