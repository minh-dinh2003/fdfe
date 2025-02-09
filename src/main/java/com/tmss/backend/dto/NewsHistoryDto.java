package com.tmss.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsHistoryDto {
    private int id;
    private int newsId;
    private Date modifyDate;
    private String description;
}
