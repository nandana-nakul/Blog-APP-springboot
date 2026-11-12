package com.nandana.blog.Controller;


import com.nandana.blog.DTO.CommentRequest;
import com.nandana.blog.DTO.CommentResponse;
import com.nandana.blog.DTO.PostRequest;
import com.nandana.blog.DTO.PostResponse;
import com.nandana.blog.Service.CommentService;
import com.nandana.blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public CommentResponse createComment(@PathVariable Integer postId, @RequestBody CommentRequest request)
    {
        return commentService.createComment(postId,request);
    }

    @DeleteMapping("/comment/{id}")

    public void deleteOneComment(@PathVariable Integer id)
    {
        commentService.deleteComment(id);
    }
}
