package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.DisciplineDTO;
import br.edu.ifpe.register.register.exceptions.NotFoundException;
import br.edu.ifpe.register.register.mapper.DisciplineMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import br.edu.ifpe.register.register.repository.DisciplineRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final CourseRepository courseRepository;
    private final DisciplineMapper disciplineMapper;

    public DisciplineService(final DisciplineRepository disciplineRepository,
                             final CourseRepository courseRepository,
                             final DisciplineMapper disciplineMapper) {
        this.disciplineRepository = disciplineRepository;
        this.courseRepository = courseRepository;
        this.disciplineMapper = disciplineMapper;
    }

    public void insertDiscipline(final DisciplineDTO discipline) {
        var course = courseRepository.findById(discipline.getCourseId())
                .orElseThrow(() ->
                        new NotFoundException("Curse not found course_id: " + discipline.getCourseId()));
        var newDiscipline = disciplineMapper.toEntity(discipline);
        newDiscipline.setCourse(course);

        disciplineRepository.save(newDiscipline);
    }
}
