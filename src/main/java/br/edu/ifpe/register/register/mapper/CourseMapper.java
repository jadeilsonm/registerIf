package br.edu.ifpe.register.register.mapper;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.dto.ResponseCourseDTO;
import br.edu.ifpe.register.register.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "id", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    CourseDTO toDto(Course course);
    ResponseCourseDTO toResponseDto(Course course);
}
