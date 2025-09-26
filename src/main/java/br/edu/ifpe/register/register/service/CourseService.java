package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.dto.ResponseCourseDTO;
import br.edu.ifpe.register.register.mapper.CourseMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(final CourseRepository courseRepository,
                         final CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public void insertCourse(final CourseDTO course) {
        this.courseRepository.save(courseMapper.toEntity(course));
    }
    public ResponseCourseDTO getCourseById(final UUID id) {
        return courseMapper.toResponseCourseDTO(this.courseRepository.findById(id).orElseThrow());
    }
    public List<ResponseCourseDTO> getAllCourses(){
        return this.courseRepository.findAll().stream().map(courseMapper::toResponseCourseDTO).collect(Collectors.toList());
    }
    public void updateCourse(final UUID id, final CourseDTO course) {
        final var existingCourse = courseRepository.findById(id).orElseThrow();
        courseMapper.updateEntity(course, existingCourse);
        courseRepository.save(existingCourse);
    }
    public void deleteCourse(final UUID id) {
        courseRepository.deleteById(id);
    }
}
