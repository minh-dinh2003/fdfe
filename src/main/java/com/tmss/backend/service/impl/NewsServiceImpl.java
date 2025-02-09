package com.tmss.backend.service.impl;

import com.tmss.backend.dto.NewsDto;
import com.tmss.backend.dto.NewsHistoryDto;
import com.tmss.backend.entity.News;
import com.tmss.backend.entity.NewsHistory;
import com.tmss.backend.entity.NewsStatus;
import com.tmss.backend.exception.ResourceNotFoundException;
import com.tmss.backend.mapper.NewsMapper;
import com.tmss.backend.mapper.NewsHistoryMapper;
import com.tmss.backend.repositories.NewsHistoryRepository;
import com.tmss.backend.repositories.NewsRepository;
import com.tmss.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsHistoryRepository newsHistoryRepository;

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        // Create and save the News entity
        News news = NewsMapper.mapToNews(newsDto);
        news.setCreateDate(new Date(System.currentTimeMillis()));
        News savedNews = newsRepository.save(news);

        return NewsMapper.mapToNewsDto(savedNews);
    }

    @Override
    public NewsDto updateNews(NewsDto newsDto, String changeDescription) {
        News existingNews = newsRepository.findById(newsDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + newsDto.getId()));

        // Update the existing news fields
        existingNews.setTitle(newsDto.getTitle());
        existingNews.setDescription(newsDto.getDescription());
        existingNews.setAuthor(newsDto.getAuthor());
        existingNews.setStatus(newsDto.getStatus());
        existingNews.setCategory(newsDto.getCategory());

        NewsHistory newsHistory = NewsHistory.builder()
                .news(existingNews)
                .modifyDate(new Date(System.currentTimeMillis()))
                .description(changeDescription)
                .build();
        newsHistoryRepository.save(newsHistory);

        // Save the updated news entity
        News updatedNews = newsRepository.save(existingNews);

        return NewsMapper.mapToNewsDto(updatedNews);
    }

    @Override
    public NewsDto getNewsById(int newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + newsId));
        return NewsMapper.mapToNewsDto(news);
    }

    @Override
    public NewsDto updateNewsStatus(int newsId, NewsStatus status) {
        News existingNews = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + newsId));

        NewsStatus oldStatus = existingNews.getStatus();
        existingNews.setStatus(status);

        // Save status change history
        NewsHistory newsHistory = NewsHistory.builder()
                .news(existingNews)
                .modifyDate(new Date(System.currentTimeMillis()))
                .description("Status changed from " + oldStatus + " to " + status)
                .build();
        newsHistoryRepository.save(newsHistory);

        // Save the updated news entity with the new status
        News updatedNews = newsRepository.save(existingNews);

        return NewsMapper.mapToNewsDto(updatedNews);
    }

    @Override
    public List<NewsDto> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(NewsMapper::mapToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNews(int newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found with ID: " + newsId));
        newsRepository.delete(news);
    }

    @Override
    public List<NewsDto> createMultipleNews(List<NewsDto> newsDtos) {
        return newsDtos.stream()
                .map(this::createNews) // Passing null for jsonFile as it's not used
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> getNewsByAuthor(String author) {
        List<News> newsList = newsRepository.findByAuthor(author);
        return newsList.stream()
                .map(NewsMapper::mapToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsHistoryDto> getNewsHistory(int newsId) {
        List<NewsHistory> historyList = newsHistoryRepository.findByNewsId(newsId);
        return historyList.stream()
                .map(NewsHistoryMapper::mapToNewsHistoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> getNewsByStatusAndAuthor(NewsStatus status, String author) {
        List<News> newsList;

        if ((status == null) && (author == null || author.isEmpty())) {
            // If both status and author are empty, return all news
            newsList = newsRepository.findAll();
        } else if (status == null) {
            // Fetch news by author only if status is empty
            newsList = newsRepository.findByAuthor(author);
        } else if (author == null || author.isEmpty()) {
            // Fetch news by status only if author is empty
            newsList = newsRepository.findByStatus(status);
        } else {
            // Fetch news by both status and author
            newsList = newsRepository.findByStatusAndAuthor(status, author);
        }

        return newsList.stream().map(NewsMapper::mapToNewsDto).collect(Collectors.toList());
    }
}
