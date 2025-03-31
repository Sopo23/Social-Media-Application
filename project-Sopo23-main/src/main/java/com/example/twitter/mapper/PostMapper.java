package com.example.twitter.mapper;

import com.example.twitter.dto.PostDto;
import com.example.twitter.entity.Post;
import com.example.twitter.user.model.ERole;
import com.example.twitter.user.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post DtoToPost(PostDto postDto);
    PostDto PostToDto(Post post);
    default Role map(String value) {
        return Role.builder()
                .name(ERole.valueOf(value))
                .build();
    }

    default String map(Role value) {
        return value.getName().toString();
    }
}
