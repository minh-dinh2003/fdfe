package com.tmss.backend.mapper;

import com.tmss.backend.dto.CommentDto;
import com.tmss.backend.entity.Comment;

public class CommentMapper {

    // Convert from Entity to DTO
    public static CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .accountId(comment.getAccountId())
                .news(comment.getNews())
                .build();
    }

    // Convert from DTO to Entity
    public static Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .accountId(commentDto.getAccountId())
                .news(commentDto.getNews())
                .build();
    }
}
