package com.nandana.blog.Service;

import com.nandana.blog.DTO.CommentRequest;
import com.nandana.blog.DTO.CommentResponse;
import com.nandana.blog.Repository.CommentEntity;
import com.nandana.blog.Repository.CommentRepository;
import com.nandana.blog.Repository.PostEntity;
import com.nandana.blog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository CommentRepository;
    @Autowired
    private PostRepository postRepository;

    public CommentResponse createComment(Integer postId, CommentRequest request) {

        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);

        if(postEntityOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostEntity postEntity = postEntityOptional.get();

        CommentEntity comment = new CommentEntity();
        comment.setAuthorName(request.getAuthorName());
        comment.setText(request.getText());
        comment.setPost(postEntity);

        CommentEntity newComment = CommentRepository.save(comment);

        CommentResponse newResponse = new CommentResponse();

        newResponse.setId(newComment.getId());
        newResponse.setAuthorName(newComment.getAuthorName());
        newResponse.setText(newComment.getText());
        newResponse.setCreatedAt(newComment.getCreatedAt());

        return newResponse;

    }

    public void deleteComment(Integer id) {
        Optional<CommentEntity> comment = CommentRepository.findById(id);

        if(comment.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CommentEntity ce = comment.get();
        CommentRepository.delete(ce);

    }
}
