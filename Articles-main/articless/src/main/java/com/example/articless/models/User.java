package com.example.articless.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String email;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 200)
    private String imageUrl;


    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 500)
    private String bio;



    public User(Long id, String email, LocalDateTime created_at, String username, String image_url, String password, String bio) {
        this.id = id;
        this.email = email;
        this.createdAt = created_at;
        this.username = username;
        this.imageUrl = image_url;
        this.password = password;
        this.bio = bio;
    }


}
