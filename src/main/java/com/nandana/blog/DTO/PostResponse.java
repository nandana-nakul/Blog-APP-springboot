package com.nandana.blog.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostResponse {

    int id;
    String title;
    String content;
    LocalDateTime createdAt;
    int commentCount;
}
