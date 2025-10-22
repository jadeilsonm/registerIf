package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.configurations.RabbitMQConfig;
import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.dto.ResponseCourseDTO;
import br.edu.ifpe.register.register.entity.Course;
import br.edu.ifpe.register.register.mapper.CourseMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import br.edu.ifpe.register.register.service.rabbit.RabbitSend;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final RabbitSend rabbitSend;

    public CourseService(final CourseRepository courseRepository,
                         final CourseMapper courseMapper,
                         final RabbitSend rabbitSend
                       ) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.rabbitSend = rabbitSend;
    }

    public void insertCourse(final CourseDTO course) {
        var courseEntity = this.courseRepository.save(courseMapper.toEntity(course));
        rabbitSend.send(courseEntity, null, RabbitMQConfig.QUEUE_COURSE_CREATED);
    }

    public List<ResponseCourseDTO> getAllCourses(){
        return this.courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }

    public Optional<ResponseCourseDTO> getCourseById(UUID id){
        return courseRepository.findById(id)
                .map(courseMapper::toResponseDto);
    }

    public Optional<CourseDTO> updateCourse(UUID id, CourseDTO courseDTO){
        return courseRepository.findById(id).map(existing -> {
            existing.setName(courseDTO.getName());
            existing.setAcronym(courseDTO.getAcronym());
            existing.setDuration(courseDTO.getDuration());
            Course updated = courseRepository.save(existing);
            rabbitSend.send(updated, null, RabbitMQConfig.QUEUE_COURSE_UPDATE);
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
