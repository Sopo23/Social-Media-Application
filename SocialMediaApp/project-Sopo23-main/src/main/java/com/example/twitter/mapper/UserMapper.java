package com.example.twitter.mapper;

import com.example.twitter.dto.UserDto;
import com.example.twitter.user.model.ERole;
import com.example.twitter.user.model.Role;
import com.example.twitter.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User DtoToUser (UserDto userDto);
    UserDto UserToDto (User user);
    default Role map(String value) {
        return Role.builder()
                .name(ERole.valueOf(value))
                .build();
    }

    default String map(Role value) {
        return value.getName().toString();
    }
}
