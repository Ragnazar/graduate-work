package ru.skypro.homework.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.dto.RegisterReqDto;
import ru.skypro.homework.model.dto.UserDto;
import ru.skypro.homework.model.entity.ProfileUser;

import java.io.IOException;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "image", expression = "java(getImage(profileUser))")
    UserDto userToUserDto(ProfileUser profileUser) throws IOException;

    default String getImage(ProfileUser profileUser) throws IOException {
        if (profileUser.getImage()==null||profileUser.getImage().isEmpty()){
            return null;
        }
        return "/users/"+profileUser.getId()+"/getImage";
    }


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "image",ignore = true)
    void partialUpdate(UserDto userDto, @MappingTarget ProfileUser profileUser);


    @Mapping(source = "username", target = "email")
    ProfileUser registerReqDtoToUser(RegisterReqDto registerReqDto);

}