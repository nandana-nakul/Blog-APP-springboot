package com.nandana.blog.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class PostDetailResponse {

    int id;
    String title;
    String content;
    LocalDateTime createdAt;
    List<CommentResponse> comments;
}
