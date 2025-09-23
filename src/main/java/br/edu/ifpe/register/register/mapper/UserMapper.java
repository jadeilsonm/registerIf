package br.edu.ifpe.register.register.mapper;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.dto.UserCsvDTO;
import br.edu.ifpe.register.register.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserDTO dto);

    @Mapping(target = "id", ignore = true)
    User ToEntityByUserCsvDTO(UserCsvDTO dto);
}
