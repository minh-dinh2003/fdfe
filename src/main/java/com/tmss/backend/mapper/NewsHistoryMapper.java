package com.tmss.backend.mapper;

import com.tmss.backend.dto.NewsHistoryDto;
import com.tmss.backend.entity.NewsHistory;

public class NewsHistoryMapper {
    public static NewsHistoryDto mapToNewsHistoryDto(NewsHistory newsHistory) {
        return NewsHistoryDto.builder()
                .id(newsHistory.getId())
                .newsId(newsHistory.getNews().getId())
                .modifyDate(newsHistory.getModifyDate())
                .description(newsHistory.getDescription())
                .build();
    }

    public static NewsHistory mapToNewsHistory(NewsHistoryDto newsHistoryDto) {
        return NewsHistory.builder()
                .modifyDate(newsHistoryDto.getModifyDate())
                .description(newsHistoryDto.getDescription())
                .build();
    }
}
