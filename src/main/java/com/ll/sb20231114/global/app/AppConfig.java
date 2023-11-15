package com.ll.sb20231114.global.app;

import com.ll.sb20231114.domain.article.article.entity.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Configuration //같은 리턴타입을 가지는 @Bean 메서드를 많이 만들어 두고, 필요할 때 가져다가 쓰면 됩니다.
// 단 변수명과 일치하는 메서드명을 가진 @Bean 메서드가 생성한 객체가 사용되는 것을 기억하세요.
// 복잡한 재활용 객체이다.

public class AppConfig {
    @Bean
    List<Article>articles2(){
        return new LinkedList<>();
    }

    @Bean
    List<Article>articles(){
        return new ArrayList<>();
    }
}
