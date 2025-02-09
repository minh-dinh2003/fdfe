package com.tmss.backend.repositories;

import com.tmss.backend.entity.News;
import com.tmss.backend.entity.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByAuthor(String author);
    List<News> findByStatus(NewsStatus status);
    List<News> findByStatusAndAuthor(NewsStatus status, String author);
}
