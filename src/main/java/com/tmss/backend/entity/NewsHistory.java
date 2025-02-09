package com.tmss.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="NewsHistory")
public class NewsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;  // Liên kết đến bài viết

    private Date modifyDate; // Ngày sửa đổi

    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả thay đổi
}
