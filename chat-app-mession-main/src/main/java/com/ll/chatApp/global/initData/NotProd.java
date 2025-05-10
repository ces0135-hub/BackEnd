package com.ll.chatApp.global.initData;

import com.ll.chatApp.domain.article.article.entity.Article;
import com.ll.chatApp.domain.article.article.repository.ArticleRepository;
import com.ll.chatApp.domain.article.article.service.ArticleService;
import com.ll.chatApp.domain.chat.chatMessage.service.ChatMessageService;
import com.ll.chatApp.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.chatApp.domain.chat.chatRoom.service.ChatRoomService;
import com.ll.chatApp.domain.member.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import com.ll.chatApp.domain.member.member.entity.Member;

import java.util.stream.IntStream;
@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    @Autowired
    @Lazy
    private NotProd self;
    private final MemberService memberService;
    private final ArticleService articleService;

    @Bean
    public ApplicationRunner initNotProdData() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        Member member1 = memberService.join("user1", "1234").getData();
        Member member2 = memberService.join("user2", "1234").getData();
        Member member3 = memberService.join("user3", "1234").getData();

        Article article1 = articleService.write(member1.getId(), "제목1", "내용1").getData();
        Article article2 = articleService.write(member1.getId(), "제목2", "내용2").getData();

        Article article3 = articleService.write(member2.getId(), "제목3", "내용3").getData();
        Article article4 = articleService.write(member2.getId(), "제목4", "내용4").getData();

        article1.addComment(member1, "댓글1");
        article1.addComment(member1, "댓글2");

        article2.addComment(member1, "댓글3");
        article2.addComment(member1, "댓글4");
        article2.addComment(member1, "댓글5");

        article3.addComment(member1, "댓글5");
        article3.addComment(member1, "댓글6");
        article3.addComment(member1, "댓글7");
        article3.addComment(member1, "댓글8");
        article3.addComment(member1, "댓글9");
        article3.addComment(member1, "댓글10");
        article3.addComment(member1, "댓글11");
        article3.addComment(member1, "댓글12");

        article1.addTag("자바");
        article1.addTag("백엔드");
        article2.addTags("프레임워크", "스프링부트");

        article4.addTags("자바", "스프링부트");

        IntStream.rangeClosed(5, 120).forEach(
                i -> {
                    String title = "제목" + i;
                    String body = "내용" + i;
                    articleService.write(member2.getId(), title, body);
                }
        );
    }
}


//@Configuration
//@Profile("!prod")
//public class NotProd {
//    @Bean
//    public ApplicationRunner applicationRunner(ChatRoomService chatRoomService,
//                                               ChatMessageService chatMessageService,
//                                               MemberService memberService,
//                                               ArticleService articleService)
//                                               // ArticleRepository articleRepository)
//    {   // 람다식은 @Transactional을 설정할 수 없어서 다음과 같이 변경
//        // "ArticleRepository articleRepository"도 위에서 필요 없어짐
//        return new ApplicationRunner() {
//            @Override
//            @Transactional
//            public void run(ApplicationArguments args) throws Exception {
//                ChatRoom chatRoom1 = chatRoomService.create("room1");
//                ChatRoom chatRoom2 = chatRoomService.create("room2");
//                ChatRoom chatRoom3 = chatRoomService.create("room3");
//
//                IntStream.rangeClosed(1, 10).forEach(num -> {
//                    chatMessageService.create(chatRoom1, "홍길동", "채팅메세지" + num);
//                });
//                Member member1 = memberService.join("user1", "1234").getData();
//                Member member2 = memberService.join("user2", "1234").getData();
//                Member member3 = memberService.join("user3", "1234").getData();
//
//                Article article1 = articleService.write(member1.getId(), "제목1", "내용1").getData();
//                Article article2 = articleService.write(member1.getId(), "제목2", "내용2").getData();
//
//                Article article3 = articleService.write(member2.getId(), "제목3", "내용3").getData();
//                Article article4 = articleService.write(member2.getId(), "제목4", "내용4").getData();
//
//                article1.addComment(member1, "댓글1");
//                article1.addComment(member1, "댓글2");
//
//                article2.addComment(member1, "댓글3");
//                article2.addComment(member1, "댓글4");
//                article2.addComment(member1, "댓글5");
//
//                article3.addComment(member1, "댓글5");
//                article3.addComment(member1, "댓글6");
//                article3.addComment(member1, "댓글7");
//                article3.addComment(member1, "댓글8");
//                article3.addComment(member1, "댓글9");
//                article3.addComment(member1, "댓글10");
//                article3.addComment(member1, "댓글11");
//                article3.addComment(member1, "댓글12");
//
//                article1.addTag("자바");
//                article1.addTag("백엔드");
//                article2.addTags("프레임워크", "스프링부트");
//                article2.addTags("자바", "스프링부트");
//            }
//        };
//    }
//}

/*
기존 람다식 표현
return args -> {
            ChatRoom chatRoom1 = chatRoomService.create("room1");
            ChatRoom chatRoom2 = chatRoomService.create("room2");
            ChatRoom chatRoom3 = chatRoomService.create("room3");

            IntStream.rangeClosed(1, 10).forEach(num -> {
                chatMessageService.create(chatRoom1, "홍길동", "채팅메세지" + num);
            });
            Member member1 = memberService.join("user1", "1234").getData();
            Member member2 = memberService.join("user2", "1234").getData();
            Member member3 = memberService.join("user3", "1234").getData();

            Article article1 = articleService.write(member1.getId(), "제목1", "내용1").getData();
            Article article2 = articleService.write(member1.getId(), "제목2", "내용2").getData();

            Article article3 = articleService.write(member2.getId(), "제목3", "내용3").getData();
            Article article4 = articleService.write(member2.getId(), "제목4", "내용4").getData();

            article1.addComment(member1, "댓글1");
            article1.addComment(member1, "댓글2");

            article2.addComment(member1, "댓글3");
            article2.addComment(member1, "댓글4");
            article2.addComment(member1, "댓글5");

            article3.addComment(member1, "댓글5");
            article3.addComment(member1, "댓글6");
            article3.addComment(member1, "댓글7");
            article3.addComment(member1, "댓글8");
            article3.addComment(member1, "댓글9");
            article3.addComment(member1, "댓글10");
            article3.addComment(member1, "댓글11");
            article3.addComment(member1, "댓글12");

            // 람다식을 사용하지 않게 변경 => 필요없어짐
            // Transactional이 없어서 더티 채킹이 안 되므로 save() 해줘야 함
            articleRepository.save(article1);
            articleRepository.save(article2);
            articleRepository.save(article3);
        };
 */