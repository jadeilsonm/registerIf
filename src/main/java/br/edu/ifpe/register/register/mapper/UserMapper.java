package br.edu.ifpe.register.register.mapper;

import br.edu.ifpe.register.register.dto.CreateUserDTO;
import br.edu.ifpe.register.register.dto.ResponseCreateUserDTO;
import br.edu.ifpe.register.register.dto.UserCsvDTO;
import br.edu.ifpe.register.register.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User toEntity(CreateUserDTO createUserDTO);

    ResponseCreateUserDTO toResponseCreateUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateEntity(CreateUserDTO createUserDTO, @org.mapstruct.MappingTarget User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User ToEntityByUserCsvDTO(UserCsvDTO userCsvDTO);
}
