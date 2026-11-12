package com.nandana.blog.Controller;

import com.nandana.blog.DTO.*;
import com.nandana.blog.Repository.PostEntity;
import com.nandana.blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")

public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostResponse> getAllPosts()
    {
        return postService.getAllPosts();
    }

    @PostMapping("/create")
    public PostResponse createPost(@RequestBody PostRequest request)
    {
        return postService.createPost(request);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Integer id, @RequestBody PostRequest pRequest)
    {
        return postService.updatePostById(id,pRequest);
    }

    @GetMapping("/{id}")
    public PostDetailResponse getPostWithComments(@PathVariable Integer id)
    {
        return postService.getPostDetails(id);
    }

    @DeleteMapping("/{id}")
    public void deletePostAndComments(@PathVariable Integer id)
    {
        postService.deletePostDetails(id);
    }









}
