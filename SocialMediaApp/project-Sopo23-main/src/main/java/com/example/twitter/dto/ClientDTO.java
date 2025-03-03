package com.example.twitter.dto;

import com.example.twitter.entity.Client;
import com.example.twitter.entity.Post;
import com.example.twitter.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private UserDto user;
    private String bio;
    private int followers;
    private int accountsFollowed;
    private List<Client> followersClient;
    private List<Client> followingClient;
    private List<Post> posts;

}
