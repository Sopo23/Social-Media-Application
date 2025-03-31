package com.example.twitter.controller;

import com.example.twitter.dto.PostAuxDTO;
import com.example.twitter.dto.PostDto;
import com.example.twitter.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.twitter.user.UrlMapping.*;

@RestController
@RequestMapping(POSTARE)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PutMapping(EDIT)
    public ResponseEntity<Void> editPost(@PathVariable Long id, @RequestParam String newPost, @RequestParam String newPoza) {
        try {
            postService.editPost(id, newPost, newPoza);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(CREATE_POST)
    public ResponseEntity<Void> createPost(@RequestBody PostAuxDTO newPostDto) {
        try {
            postService.createPost(newPostDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(LIKE)
    public ResponseEntity<Void> likeAPost(@PathVariable Long id) {
        try {
            postService.likeAPost(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(REPLY)
    public ResponseEntity<Void> addReply(@PathVariable Long id, @RequestBody PostAuxDTO reply) {
        try {
            postService.addReply(id, reply);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(SHOW_POST)
    public ResponseEntity<List<PostAuxDTO>> showAllPostsWithoutParent() {
        List<PostAuxDTO> posts = postService.showAllPostsWithoutParent();
        return ResponseEntity.ok(posts);
    }
    @GetMapping(SHOW_REPLYS)
    public ResponseEntity<List<PostAuxDTO>> showRepliesOfAPost(@PathVariable Long id) {
        try {
            List<PostAuxDTO> replies = postService.showReplysOfAPost(id);
            return ResponseEntity.ok(replies);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
