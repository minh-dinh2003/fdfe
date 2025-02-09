package com.tmss.backend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmss.backend.dto.NewsDto;
import com.tmss.backend.entity.News;

public class NewsMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

    public static NewsDto mapToNewsDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .author(news.getAuthor())
                .createDate(news.getCreateDate())
                .approver(news.getApprover())
                .content(news.getContent())
                .status(news.getStatus())
                .category(news.getCategory())
                .build();
    }

    public static News mapToNews(NewsDto newsDto) {
        return News.builder()
                .id(newsDto.getId())
                .title(newsDto.getTitle())
                .description(newsDto.getDescription())
                .author(newsDto.getAuthor())
                .createDate(newsDto.getCreateDate())
                .approver(newsDto.getApprover())
                .content(newsDto.getContent())
                .status(newsDto.getStatus())
                .category(newsDto.getCategory())
                .build();
    }

    public static String mapToJson(News newsDto) {
        try {
            return objectMapper.writeValueAsString(newsDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting NewsDto to JSON", e);
        }
    }



}
