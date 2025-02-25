package com.example.articless.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(length = 50)
    private String description;

    @Column(nullable = false, length = 50, unique = true)
    private String slug;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String content;

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    public Article(Long id, LocalDateTime created_at, LocalDateTime updated_at, User author, String description, String slug, String title, String content, List<Tag> tags) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.author = author;
        this.description = description;
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

}

