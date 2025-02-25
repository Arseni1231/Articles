package com.example.articless.config;

import com.example.articless.models.Article;
import com.example.articless.models.Tag;
import com.example.articless.models.User;
import com.example.articless.repositories.ArticleRepository;
import com.example.articless.repositories.TagRepository;
import com.example.articless.repositories.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InsertData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public InsertData(UserRepository userRepository, ArticleRepository articleRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Insert data is starting...");

        if (userRepository.count() > 0) {
            return;
        }

        // Создаем объект Faker для генерации случайных данных
        Faker faker = new Faker();

        // Генерация случайных пользователей
        User user1 = new User(null, faker.internet().emailAddress(), LocalDateTime.now(), faker.name().username(), faker.internet().image(), faker.internet().password(), faker.lorem().paragraph()
        );

        User user2 = new User(null, faker.internet().emailAddress(), LocalDateTime.now(), faker.name().username(), faker.internet().image(), faker.internet().password(), faker.lorem().paragraph()
        );

        // Сохраняем пользователей в базе данных
        userRepository.saveAll(List.of(user1, user2));

        // Генерация случайных тегов
        Tag tag1 = new Tag(null, LocalDateTime.now(), null, faker.lorem().word());
        Tag tag2 = new Tag(null, LocalDateTime.now(), null, faker.lorem().word());
        Tag tag3 = new Tag(null, LocalDateTime.now(), null, faker.lorem().word());

        tagRepository.saveAll(List.of(tag1, tag2, tag3));

        // Генерация случайных статей
        Article article1 = new Article(null, LocalDateTime.now(),
                null, user1, faker.lorem().sentence(), faker.lorem().word(), faker.lorem().sentence(), faker.lorem().paragraph(), List.of(tag1, tag2)
        );

        Article article2 = new Article(
                null, LocalDateTime.now(), null, user2, faker.lorem().sentence(), faker.lorem().word(), faker.lorem().sentence(), faker.lorem().paragraph(), List.of(tag2, tag3)
        );

        // Связываем теги с статьями
        tag1.setArticles(List.of(article1));
        tag2.setArticles(List.of(article1, article2));
        tag3.setArticles(List.of(article2));

        // Сохраняем статьи и теги в базе данных
        articleRepository.saveAll(List.of(article1, article2));
        tagRepository.saveAll(List.of(tag1, tag2, tag3));

        System.out.println("Данные успешно загружены!");
    }
}


