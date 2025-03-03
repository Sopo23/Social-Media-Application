package com.example.twitter.entity;

import com.example.twitter.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @Column(name = "bio")
    private String bio;
    @Column(name = "followersNumber")
    private int followers;
    @Column(name = "accountsFollowedNumber")
    private int accountsFollowed; @ManyToMany
    @JoinTable(
            name = "client_followers",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<Client> followersClient;
    @ManyToMany
    @JoinTable(
            name = "client_following",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<Client> followingAccounts;
    @OneToMany
    private List<Post> posts;

}
