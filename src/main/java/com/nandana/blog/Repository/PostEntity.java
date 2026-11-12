package com.nandana.blog.Repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments = new ArrayList<>();

    @PrePersist
    void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
