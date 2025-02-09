package com.tmss.backend.dto;
import com.tmss.backend.entity.NewsStatus;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private int id;
    private String title;
    private String description;
    private String author;
    private Date createDate;
    private String approver;
    private String content;
    private NewsStatus status;
    private int category;
}
