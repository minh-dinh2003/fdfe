package com.tmss.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="News")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String author;
    private Date createDate;
    private String approver;
    private String content;
    private NewsStatus status;
    private int category;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent serialization of the history list
    private List<NewsHistory> history;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Prevents serialization of the comments list
    private List<Comment> comments;
}
