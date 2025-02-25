package com.example.articless.repositories;

import com.example.articless.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContainingIgnoreCase(String title);
    List<Article> findByAuthor_Username(String username);
    List<Article> findByTags_Name(String tagName);

    @Query("SELECT a FROM Article a WHERE YEAR(a.created_at) = YEAR(CURRENT_DATE) AND MONTH(a.created_at) = MONTH(CURRENT_DATE)")
    List<Article> findArticlesPublishedThisMonth();

    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t.name = :tagName")
    List<Article> findByTagName(@Param("tagName") String tagName);
}
