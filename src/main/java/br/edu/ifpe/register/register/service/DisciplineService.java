package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.configurations.RabbitMQConfig;
import br.edu.ifpe.register.register.dto.DisciplineDTO;
import br.edu.ifpe.register.register.dto.ResponseDisciplineDTO;
import br.edu.ifpe.register.register.exceptions.NotFoundException;
import br.edu.ifpe.register.register.mapper.DisciplineMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import br.edu.ifpe.register.register.repository.DisciplineRepository;
import br.edu.ifpe.register.register.service.rabbit.RabbitSend;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final CourseRepository courseRepository;
    private final DisciplineMapper disciplineMapper;
    private final RabbitSend rabbitSend;

    public DisciplineService(final DisciplineRepository disciplineRepository,
                             final CourseRepository courseRepository,
                             final DisciplineMapper disciplineMapper,
                             final RabbitSend rabbitSend
    ) {
        this.disciplineRepository = disciplineRepository;
        this.courseRepository = courseRepository;
        this.disciplineMapper = disciplineMapper;
        this.rabbitSend = rabbitSend;
    }

    public void insertDiscipline(final DisciplineDTO discipline) {
        var course = courseRepository.findById(discipline.getCourseId())
                .orElseThrow(() ->
                        new NotFoundException("Curse not found course_id: " + discipline.getCourseId()));
        var newDiscipline = disciplineMapper.toEntity(discipline);
        newDiscipline.setCourse(course);

        var result = disciplineRepository.save(newDiscipline);
        rabbitSend.send(result, null, RabbitMQConfig.QUEUE_DISCIPLINE_CREATED);
    }
    public List<ResponseDisciplineDTO> getAllDisciplines(){
        return this.disciplineRepository.findAll().stream().map(disciplineMapper::toResponseDisciplineDTO).collect(Collectors.toList());
    }
    public ResponseDisciplineDTO getDisciplineById(final UUID id) {
        return disciplineMapper.toResponseDisciplineDTO(this.disciplineRepository.findById(id).orElseThrow(
                () ->
                        new NotFoundException("Discipline not found discipline_id: " + id)
        ));
    }
    public void updateDiscipline(final UUID id, final DisciplineDTO discipline) {
        final var existingDiscipline = disciplineRepository.findById(id).orElseThrow();
        disciplineMapper.updateEntity(discipline, existingDiscipline);
        if (!existingDiscipline.getCourse().getId().equals(discipline.getCourseId())) {
            var course = courseRepository.findById(discipline.getCourseId())
                    .orElseThrow(() ->
                            new NotFoundException("Curse not found course_id: " + discipline.getCourseId()));
            existingDiscipline.setCourse(course);
        }
        var result = disciplineRepository.save(existingDiscipline);
        rabbitSend.send(result, null, RabbitMQConfig.QUEUE_DISCIPLINE_UPDATE);
    }
    public void deleteDiscipline(final UUID id) {
        disciplineRepository.deleteById(id);
    }
}
