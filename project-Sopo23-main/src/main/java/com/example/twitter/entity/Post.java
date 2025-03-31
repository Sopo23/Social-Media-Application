package com.example.twitter.entity;

import com.example.twitter.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Client client;
    @Column(name = "post")
    private String post;

    @Column(name = "image")
    private String image;
    @Column(name = "likes")
    private int like;
    @Column(name = "replys")
    @OneToMany
    private List<Post> replys;
    @ManyToOne
    private Post parentpost;


}
