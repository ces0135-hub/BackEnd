package com.example.demo.global.initData;

import com.example.demo.article.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Init {
    @Bean
    CommandLineRunner initData(ArticleService articleService) {
        return args -> {
            articleService.write("제목 1", "내용 1");
            articleService.write("제목 2", "내용 2");
            articleService.write("제목 3", "내용 3");
            articleService.write("제목 4", "내용 4");
            articleService.write("제목 5", "내용 5");
            articleService.write("제목 6", "내용 6");
        };
    }
}