package com.example.twitter.repository;

import com.example.twitter.entity.Post;
import com.example.twitter.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long id);
    @Query(value = "DELETE FROM posts WHERE id=?1", nativeQuery = true)
    void deletePost(Long id);
}
