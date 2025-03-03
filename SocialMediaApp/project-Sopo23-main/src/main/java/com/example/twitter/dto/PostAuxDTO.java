package com.example.twitter.dto;

import com.example.twitter.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAuxDTO {
    private Long id;
    private Long clientName;
    private String post;
    private String image;


}
