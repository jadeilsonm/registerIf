package br.edu.ifpe.register.register.mapper;

import br.edu.ifpe.register.register.dto.CourseDTO;
import br.edu.ifpe.register.register.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "id", ignore = true)
    Course toEntity(CourseDTO courseDTO);
    CourseDTO toDto(Course course);
    
    @Mapping(target = "id", ignore = true)
    void updateEntity(CourseDTO courseDTO, @MappingTarget Course course);
}
