package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.mapper.CourseMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import org.springframework.stereotype.Service;

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
}
