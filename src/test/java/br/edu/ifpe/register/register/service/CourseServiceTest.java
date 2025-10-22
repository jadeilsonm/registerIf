package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.dto.ResponseCourseDTO;
import br.edu.ifpe.register.register.entity.Course;
import br.edu.ifpe.register.register.mapper.CourseMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

//    @Test
//    void insertCourseShouldSaveCourse() {
//        CourseDTO courseDTO = new CourseDTO("Course Name", "CN", 4);
//        Course course = new Course(UUID.randomUUID(), "Course Name", "CN", 4);
//
//        when(courseMapper.toEntity(courseDTO)).thenReturn(course);
//
//        courseService.insertCourse(courseDTO);
//
//        verify(courseRepository).save(course);
//    }

    @Test
    void getAllCoursesShouldReturnListOfCourses() {
        Course course = new Course(UUID.randomUUID(), "Course Name", "CN", 4);
        ResponseCourseDTO responseCourseDTO = new ResponseCourseDTO(course.getId(),"Course Name", "CN", 4);

        when(courseRepository.findAll()).thenReturn(List.of(course));
        when(courseMapper.toResponseDto(course)).thenReturn(responseCourseDTO);

        List<ResponseCourseDTO> result = courseService.getAllCourses();

        assertEquals(1, result.size());
        assertEquals(responseCourseDTO, result.getFirst());
    }

    @Test
    void getCourseByIdShouldReturnCourseWhenFound() {
        UUID id = UUID.randomUUID();
        Course course = new Course(id, "Course Name", "CN", 4);
        ResponseCourseDTO responseCourseDTO = new ResponseCourseDTO(id,"Course Name", "CN", 4);

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(courseMapper.toResponseDto(course)).thenReturn(responseCourseDTO);

        Optional<ResponseCourseDTO> result = courseService.getCourseById(id);

        assertTrue(result.isPresent());
        assertEquals(responseCourseDTO, result.get());
    }

    @Test
    void getCourseByIdShouldReturnEmptyWhenNotFound() {
        UUID id = UUID.randomUUID();

        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        Optional<ResponseCourseDTO> result = courseService.getCourseById(id);

        assertTrue(result.isEmpty());
    }

//    @Test
//    void updateCourseShouldUpdateAndReturnUpdatedCourse() {
//        UUID id = UUID.randomUUID();
//        CourseDTO courseDTO = new CourseDTO("Updated Name", "UN", 5);
//        Course existingCourse = new Course(id, "Old Name", "ON", 4);
//        Course updatedCourse = new Course(id, "Updated Name", "UN", 5);
//
//        when(courseRepository.findById(id)).thenReturn(Optional.of(existingCourse));
//        when(courseRepository.save(existingCourse)).thenReturn(updatedCourse);
//        when(courseMapper.toDto(updatedCourse)).thenReturn(courseDTO);
//
//        Optional<CourseDTO> result = courseService.updateCourse(id, courseDTO);
//
//        assertTrue(result.isPresent());
//        assertEquals(courseDTO, result.get());
//    }

    @Test
    void updateCourseShouldReturnEmptyWhenCourseNotFound() {
        UUID id = UUID.randomUUID();
        CourseDTO courseDTO = new CourseDTO("Updated Name", "UN", 5);

        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CourseDTO> result = courseService.updateCourse(id, courseDTO);

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteCourseShouldReturnTrueWhenCourseExists() {
        UUID id = UUID.randomUUID();
        Course course = new Course(id, "Course Name", "CN", 4);

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));

        boolean result = courseService.deleteCourse(id);

        assertTrue(result);
        verify(courseRepository).delete(course);
    }

    @Test
    void deleteCourseShouldReturnFalseWhenCourseNotFound() {
        UUID id = UUID.randomUUID();

        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        boolean result = courseService.deleteCourse(id);

        assertFalse(result);
    }
}
