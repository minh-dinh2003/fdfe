package com.tmss.backend.repositories;

import com.tmss.backend.entity.NewsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsHistoryRepository extends JpaRepository<NewsHistory, Integer> {
    List<NewsHistory> findByNewsId(int newsId);
}