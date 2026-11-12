package com.nandana.blog.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentResponse {

    int id;
    String authorName;
    String text;
    LocalDateTime createdAt;
}
