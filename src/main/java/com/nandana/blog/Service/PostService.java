package com.nandana.blog.Service;

import com.nandana.blog.DTO.CommentResponse;
import com.nandana.blog.DTO.PostDetailResponse;
import com.nandana.blog.DTO.PostRequest;
import com.nandana.blog.DTO.PostResponse;
import com.nandana.blog.Repository.CommentEntity;
import com.nandana.blog.Repository.PostEntity;
import com.nandana.blog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;


    public List<PostResponse> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        for(PostEntity p : posts)
        {
            PostResponse pr = new PostResponse();
            pr.setId(p.getId());
            pr.setTitle(p.getTitle());
            pr.setContent(p.getContent());
            pr.setCreatedAt(p.getCreatedAt());
            pr.setCommentCount(p.getComments().size());

            responses.add(pr);
        }
        return responses;
    }

    public PostResponse createPost(PostRequest request) {

        PostEntity pe= new PostEntity();
        pe.setTitle(request.getTitle());
        pe.setContent(request.getContent());

        PostEntity newlyCreatedPost = postRepository.save(pe);

        // CREATING A NEW POSTRESPONSE SO THAT WE CAN RETURN THE NEWLY CREATED POST
        PostResponse newPs = new PostResponse();
        newPs.setId(newlyCreatedPost.getId());
        newPs.setTitle(newlyCreatedPost.getTitle());
        newPs.setCreatedAt(newlyCreatedPost.getCreatedAt());
        newPs.setContent(newlyCreatedPost.getContent());
        newPs.setCommentCount(newlyCreatedPost.getComments().size());

        return newPs;
    }


    public PostResponse updatePostById(Integer id, PostRequest pRequest) {
        Optional<PostEntity> post = postRepository.findById(id);

        if(post.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        PostEntity pe = post.get();


        pe.setTitle(pRequest.getTitle());
        pe.setContent(pRequest.getContent());

        PostEntity updatedPe = postRepository.save(pe);

        // CREATING A NEW POSTRESPONSE SO THAT WE CAN RETURN THE NEWLY CREATED POST
        PostResponse updatedPr = new PostResponse();
        updatedPr.setId(updatedPe.getId());
        updatedPr.setTitle(updatedPe.getTitle());
        updatedPr.setCreatedAt(updatedPe.getCreatedAt());
        updatedPr.setContent(updatedPe.getContent());
        updatedPr.setCommentCount(updatedPe.getComments().size());

        return updatedPr;

    }


    public PostDetailResponse getPostDetails(Integer id) {

        Optional<PostEntity> peOptional = postRepository.findById(id);

        if(peOptional.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PostEntity postEntity = peOptional.get();

        PostDetailResponse postDetailResponse = new PostDetailResponse();

        postDetailResponse.setId(postEntity.getId());
        postDetailResponse.setTitle(postEntity.getTitle());
        postDetailResponse.setContent(postEntity.getContent());
        postDetailResponse.setCreatedAt(postEntity.getCreatedAt());

        List<CommentEntity> commentEntities = postEntity.getComments();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for( CommentEntity commentEntity : commentEntities){

            CommentResponse cr = new CommentResponse();

            cr.setAuthorName(commentEntity.getAuthorName());
            cr.setText(commentEntity.getText());
            cr.setCreatedAt(commentEntity.getCreatedAt());
            cr.setId(commentEntity.getId());

            commentResponses.add(cr);
        }

        postDetailResponse.setComments(commentResponses);

        return postDetailResponse;

    }

    public void deletePostDetails(Integer id) {
        Optional<PostEntity>  post = postRepository.findById(id);

        if(post.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PostEntity postEntity = post.get();

        postRepository.delete(postEntity);


    }
}
