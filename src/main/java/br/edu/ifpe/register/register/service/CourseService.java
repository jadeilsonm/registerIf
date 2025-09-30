package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.entity.Course;
import br.edu.ifpe.register.register.mapper.CourseMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public List<CourseDTO> getAllCourses(){
        return this.courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    public Optional<CourseDTO> getCourseById(UUID id){
        return courseRepository.findById(id)
                .map(courseMapper::toDto);
    }

    public Optional<CourseDTO> updateCourse(UUID id, CourseDTO courseDTO){
        return courseRepository.findById(id).map(existing -> {
            existing.setName(courseDTO.getName());
            existing.setAcronym(courseDTO.getAcronym());
            existing.setDuration(courseDTO.getDuration());
            Course updated = courseRepository.save(existing);
            return courseMapper.toDto(updated);
        });
    }

    public boolean deleteCourse(UUID id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
}
