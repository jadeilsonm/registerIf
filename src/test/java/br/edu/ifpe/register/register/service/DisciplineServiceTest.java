package br.edu.ifpe.register.register.service;

import br.edu.ifpe.register.register.dto.DisciplineDTO;
import br.edu.ifpe.register.register.entity.Course;
import br.edu.ifpe.register.register.entity.Discipline;
import br.edu.ifpe.register.register.exceptions.NotFoundException;
import br.edu.ifpe.register.register.mapper.DisciplineMapper;
import br.edu.ifpe.register.register.repository.CourseRepository;
import br.edu.ifpe.register.register.repository.DisciplineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DisciplineServiceTest {
    @Mock
    private DisciplineRepository disciplineRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private DisciplineMapper disciplineMapper;

    @InjectMocks
    private DisciplineService disciplineService;

    @Test
    void insertDisciplineShouldSaveDisciplineWhenCourseExists() {
        UUID courseId = UUID.randomUUID();
        DisciplineDTO disciplineDTO = new DisciplineDTO(courseId,"Discipline Name", 10);
        Course course = new Course(courseId, "Course Name", "CN", 4);
        Discipline discipline = new Discipline(UUID.randomUUID(), course,"Discipline Name", 40);

        when(courseRepository.findById(disciplineDTO.getCourseId())).thenReturn(Optional.of(course));
        when(disciplineMapper.toEntity(disciplineDTO)).thenReturn(discipline);

        disciplineService.insertDiscipline(disciplineDTO);

        verify(disciplineRepository).save(discipline);
    }

    @Test
    void insertDisciplineShouldThrowNotFoundExceptionWhenCourseDoesNotExist() {
        DisciplineDTO disciplineDTO = new DisciplineDTO(UUID.randomUUID(),"Discipline Name", 6);

        when(courseRepository.findById(disciplineDTO.getCourseId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> disciplineService.insertDiscipline(disciplineDTO));
    }


    @Test
    void getDisciplineByIdShouldThrowExceptionWhenNotFound() {
        UUID id = UUID.randomUUID();

        when(disciplineRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> disciplineService.getDisciplineById(id));
    }


    @Test
    void deleteDisciplineShouldDeleteDisciplineWhenFound() {
        UUID id = UUID.randomUUID();

        disciplineService.deleteDiscipline(id);

        verify(disciplineRepository).deleteById(id);
    }
}
