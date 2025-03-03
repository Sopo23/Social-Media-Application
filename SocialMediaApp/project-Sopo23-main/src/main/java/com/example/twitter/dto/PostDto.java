package com.example.twitter.dto;

import com.example.twitter.entity.Post;
import com.example.twitter.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private ClientDTO client;
    private String post;
    private String image;
    private int like;
    private List<Post> replys;

    private Post parentpost;

}
