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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InsertData implements CommandLineRunner {

    private UserRepository userRepository;

    private ArticleRepository articleRepository;

    private TagRepository tagRepository;


    private Faker faker = new Faker();

    Random random = new Random();

    public InsertData(ArticleRepository articleRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional



    public void run(String... args) throws Exception {
        seedUsers();
        seedTag();
        seedArticles();
        }


        private void seedUsers() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setUsername(faker.name().username());
            user.setPassword("228228");
            user.setBio(faker.lorem().sentence());
            userRepository.save(user);

        }
        }

        private void seedTag() {
        List<String> tagNames=List.of("Technology", "IT", "AI", "Developer");
        for (String tagName : tagNames) {
            Tag tag = new Tag();
            tag.setName(tagName);
            tagRepository.save(tag);
        }
        }



        private void seedArticles() {
            List<User> authors = userRepository.findAll();
            List<Tag> tags = tagRepository.findAll();
            for (int i = 0; i < 5; i++) {
            Article article = new Article();

            article.setAuthor(authors.get(random.nextInt(authors.size())));
            article.setTitle(faker.book().title());
            article.setSlug(article.getTitle().toLowerCase().replaceAll("[^a-zA-Z0-9]", "")
                    .replaceAll("\\s+", "-") + "-" + 1);
            article.setContent(faker.lorem().paragraph(5));
            article.setDescription(faker.lorem().sentence());
            int tagCount = random.nextInt(3) + 1;
            if(article.getTags() == null){
                article.setTags(new ArrayList<>());
        }
            for(int j = 0; j < tagCount; j++){
                article.getTags().add(tags.get(random.nextInt(tags.size())));
            }
            articleRepository.save(article);
        }

        System.out.println("Данные успешно загружены!");
    }
}


