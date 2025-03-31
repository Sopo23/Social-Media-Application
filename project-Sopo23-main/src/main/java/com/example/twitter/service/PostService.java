package com.example.twitter.service;

import com.example.twitter.dto.PostAuxDTO;
import com.example.twitter.dto.PostDto;
import com.example.twitter.entity.Client;
import com.example.twitter.entity.Post;
import com.example.twitter.mapper.ClientMapper;
import com.example.twitter.mapper.PostAuxMapper;
import com.example.twitter.mapper.PostMapper;
import com.example.twitter.repository.ClientRepository;
import com.example.twitter.repository.PostRepository;
import com.example.twitter.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PostMapper postMapper;
    private final PostAuxMapper postAuxMapper;
    private final ClientMapper clientMapperent;

    @Transactional
    public void editPost(Long id, String newPost,String newPoza){
        Optional<Post> post = postRepository.findById(id);
        Post currentPost = post.get();
        currentPost.setPost(newPost);
        currentPost.setImage(newPoza);
        postRepository.save(currentPost);
    }
    /*id:1*/
@Transactional
    public void createPost(PostAuxDTO newPostDto){
        Post post = Post.builder()
                .id(postAuxMapper.DtoToPost(newPostDto).getId())
                .client(clientRepository.findByUser_Id(newPostDto.getClientName()))
                .post(postAuxMapper.DtoToPost(newPostDto).getPost())
                .image(postAuxMapper.DtoToPost(newPostDto).getImage())
                .like(0)
                .replys(new ArrayList<>())
                .build();
        Client client = clientRepository.findByUser_Id(newPostDto.getClientName());
        List<Post> postari = client.getPosts();
        postari.add(postRepository.save(post));
        client.setPosts(new ArrayList<>(postari));
        clientRepository.save(client);
    }
    @Transactional
    public void likeAPost(Long id){
        Post post = postRepository.findById(id).get();
        post.setLike(post.getLike() + 1);
        postRepository.save(post);
    }
    @Transactional
    public void addReply(Long id, PostAuxDTO reply){
        Post postCurrent = postRepository.findById(id).get();
        Post replyCurrent = Post.builder()
                .id(postAuxMapper.DtoToPost(reply).getId())
                .client(clientRepository.findByUser_Id(reply.getClientName()))
                .post(postAuxMapper.DtoToPost(reply).getPost())
                .image(postAuxMapper.DtoToPost(reply).getImage())
                .like(postAuxMapper.DtoToPost(reply).getLike())
                .parentpost(postCurrent)
                .replys(new ArrayList<>())
                .build();
        postRepository.save(replyCurrent);
        List<Post> postari = postCurrent.getReplys();
        postari.add(replyCurrent);
        postCurrent.setReplys(postari);
        postRepository.save(postCurrent);
        Client client = clientRepository.findByUser_Id(reply.getClientName());
        List<Post> posts = client.getPosts();
        posts.add(replyCurrent);
        client.setPosts(posts);
        clientRepository.save(client);

    }
    @Transactional
    public List<PostAuxDTO> showAllPostsWithoutParent() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .filter(post -> post.getParentpost() == null)
                .map(postAuxMapper::PostToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<PostAuxDTO> showReplysOfAPost(Long id){
    Post post = postRepository.findById(id).get();
    List<PostAuxDTO> list = new ArrayList<>();
    for(Post pt : post.getReplys()){
        list.add(postAuxMapper.PostToDto(pt));
    }
    return list;
    }
    @Transactional
    public void deletePost(Long id){
        postRepository.deletePost(id);
    }
}
