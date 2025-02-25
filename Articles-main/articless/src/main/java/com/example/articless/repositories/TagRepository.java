package com.example.articless.repositories;

import com.example.articless.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameIgnoreCase(String name);

    List<Tag> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT t FROM Tag t JOIN t.articles a WHERE a.id = :articleId")
    List<Tag> findTagsByArticleId(@Param("articleId") Long articleId);
}
