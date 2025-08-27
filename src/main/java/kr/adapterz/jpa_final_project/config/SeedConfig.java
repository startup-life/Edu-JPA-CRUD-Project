package kr.adapterz.jpa_final_project.config;

import kr.adapterz.jpa_final_project.entity.Post;
import kr.adapterz.jpa_final_project.entity.User;
import kr.adapterz.jpa_final_project.repository.PostRepository;
import kr.adapterz.jpa_final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class SeedConfig {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Bean
    ApplicationRunner seedRunner() {
        return args -> seed(); // 부트 기동 후 1회 실행
    }

    @Transactional
    void seed() {
        if (userRepository.count() >= 10 && postRepository.count() >= 10) return;

        IntStream.rangeClosed(1, 10).forEach(i -> {
            User user = new User("tester"+i+"@adapterz.kr", "123aS!"+i, "tester"+i);
            userRepository.save(user);

            Post post = new Post("title"+i, "content"+i, user);
            postRepository.save(post);
        });
    }
}