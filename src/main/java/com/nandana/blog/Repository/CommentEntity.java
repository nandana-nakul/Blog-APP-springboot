package com.nandana.blog.Repository;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Integer id;

    String authorName;

    String text;

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id" , nullable = false)
    private PostEntity post;


    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
