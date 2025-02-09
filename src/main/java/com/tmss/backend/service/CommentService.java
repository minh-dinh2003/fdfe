package com.tmss.backend.service;

import com.tmss.backend.dto.CommentDto;
import com.tmss.backend.entity.Comment;
import java.util.List;

public interface CommentService{
    CommentDto addComment(CommentDto comment, int newsId);
    List<CommentDto> getCommentsByNewsId(int newsId);
    void deleteComment(int commentId);
}
