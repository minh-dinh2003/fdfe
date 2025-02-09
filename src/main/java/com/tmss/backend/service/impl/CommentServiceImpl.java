package com.tmss.backend.service.impl;

import com.tmss.backend.dto.CommentDto;
import com.tmss.backend.entity.Comment;
import com.tmss.backend.entity.News;
import com.tmss.backend.entity.NewsStatus;
import com.tmss.backend.mapper.CommentMapper;
import com.tmss.backend.repositories.CommentRepository;
import com.tmss.backend.repositories.NewsRepository;
import com.tmss.backend.service.CommentService;
import com.tmss.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final NewsService newsService;
    private final NewsRepository newsRepository;

    @Override
    public CommentDto addComment(CommentDto commentDto, int newsId) {
        Optional<News> newsOptional = newsRepository.findById(newsId); // Sửa thành Optional
        if (newsOptional.isPresent()) {
            Comment comment = CommentMapper.mapToComment(commentDto); // Convert DTO to Entity
            comment.setNews(newsOptional.get()); // Sửa thành newsOptional.get()
            Comment savedComment = commentRepository.save(comment); // Save the entity
            newsService.updateNewsStatus(newsId, NewsStatus.REJECTED);
            return CommentMapper.mapToCommentDto(savedComment); // Convert saved entity back to DTO
        } else {
            throw new RuntimeException("News with ID " + newsId + " not found.");
        }
    }


    @Override
    public List<CommentDto> getCommentsByNewsId(int newsId) {
        List<Comment> comments = commentRepository.findByNewsId(newsId);
        return comments.stream()
                .map(CommentMapper::mapToCommentDto) // Chuyển đổi từ Entity sang DTO
                .toList();
    }


    @Override
    public void deleteComment(int commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new RuntimeException("Comment with ID " + commentId + " not found.");
        }
    }
}
