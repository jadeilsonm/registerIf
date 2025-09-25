package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.mapper.CourseMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

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
    public CourseDTO getCourseById(final UUID id) {
        return courseMapper.toDto(this.courseRepository.findById(id).orElseThrow());
    }
    public List<CourseDTO> getAllCourses(){
        return this.courseRepository.findAll().stream().map(courseMapper::toDto).collect(Collectors.toList());
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
