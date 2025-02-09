package com.tmss.backend.controller;

import com.tmss.backend.dto.CommentDto;
import com.tmss.backend.dto.NewsDto;
import com.tmss.backend.dto.NewsHistoryDto;
import com.tmss.backend.entity.Comment;
import com.tmss.backend.entity.NewsStatus;
import com.tmss.backend.service.CommentService;
import com.tmss.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<NewsDto> createNews(
            @RequestBody NewsDto newsDto
    ) {
        NewsDto createdNews = newsService.createNews(newsDto);
        return ResponseEntity.ok(createdNews);
    }
    @GetMapping("/comment/{newsId}")
    public ResponseEntity<List<CommentDto>> getCommentsByNewsId(@PathVariable int newsId) {
        List<CommentDto> comments = commentService.getCommentsByNewsId(newsId);
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<NewsDto>> createMultipleNews(@RequestBody List<NewsDto> newsDtos) {
        List<NewsDto> createdNewsList = newsService.createMultipleNews(newsDtos);
        return ResponseEntity.ok(createdNewsList);
    }
    @GetMapping("/search")
    public ResponseEntity<List<NewsDto>> getNewsByStatusAndAuthor(
            @RequestParam(value = "status", required = false) NewsStatus status,
            @RequestParam(value = "author", required = false) String author
    ) {
        List<NewsDto> newsList = newsService.getNewsByStatusAndAuthor(status, author);
        return ResponseEntity.ok(newsList);
    }

    @PutMapping
    public ResponseEntity<NewsDto> updateNews(
            @RequestBody NewsDto newsDto,
            @RequestParam String changeDescription
    ) {
        NewsDto updatedNews = newsService.updateNews(newsDto, changeDescription);
        return ResponseEntity.ok(updatedNews);
    }


    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable("id") int newsId) {
        NewsDto news = newsService.getNewsById(newsId);
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        List<NewsDto> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<NewsDto> updateNewsStatus(
            @PathVariable("id") int newsId,
            @RequestParam("status") NewsStatus status
    ) {
        NewsDto updatedNews = newsService.updateNewsStatus(newsId, status);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable("id") int newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<NewsHistoryDto>> getNewsHistory(@PathVariable("id") int newsId) {
        List<NewsHistoryDto> historyList = newsService.getNewsHistory(newsId);
        return ResponseEntity.ok(historyList);
    }
    @GetMapping("/author")
    public ResponseEntity<List<NewsDto>> getNewsByAuthor(@RequestParam("author") String author) {
        List<NewsDto> newsList = newsService.getNewsByAuthor(author);
        return ResponseEntity.ok(newsList);
    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(
            @PathVariable("id") int newsId,
            @RequestBody CommentDto comment
    ) {
        CommentDto createdComment = commentService.addComment(comment, newsId);
        return ResponseEntity.ok(createdComment);
    }
}
