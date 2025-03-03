package com.example.twitter.mapper;

import com.example.twitter.dto.PostAuxDTO;
import com.example.twitter.dto.PostDto;
import com.example.twitter.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PostAuxMapper {
    Post DtoToPost(PostAuxDTO postDto);
    PostAuxDTO PostToDto(Post post);
}
