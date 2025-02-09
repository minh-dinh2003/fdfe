package com.tmss.backend.dto;

import com.tmss.backend.entity.News;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    private String accountId;
    private News news;
}
