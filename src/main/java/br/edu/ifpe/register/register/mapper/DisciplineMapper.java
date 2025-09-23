package br.edu.ifpe.register.register.mapper;


import br.edu.ifpe.register.register.dto.DisciplineDTO;
import br.edu.ifpe.register.register.entity.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    Discipline toEntity(DisciplineDTO disciplineDTO);

}
