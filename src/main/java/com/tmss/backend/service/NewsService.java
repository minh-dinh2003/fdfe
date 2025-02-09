package com.tmss.backend.service;

import com.tmss.backend.dto.NewsDto;
import com.tmss.backend.dto.NewsHistoryDto;
import com.tmss.backend.entity.NewsStatus;

import java.util.List;

public interface NewsService {
    NewsDto createNews(NewsDto newsDto);
    NewsDto updateNews(NewsDto newsDto, String description);
    NewsDto getNewsById(int newsId);
    NewsDto updateNewsStatus(int newsId, NewsStatus status);
    List<NewsDto> getAllNews();
    void deleteNews(int newsId);
    List<NewsDto> createMultipleNews(List<NewsDto> newsDtos);
    List<NewsDto> getNewsByAuthor(String author);
    // Methods for handling News history
    List<NewsHistoryDto> getNewsHistory(int newsId);
    List<NewsDto> getNewsByStatusAndAuthor(NewsStatus status, String author);
}
