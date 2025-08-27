package kr.adapterz.jpa_final_project.service;

import kr.adapterz.jpa_final_project.entity.Post;
import kr.adapterz.jpa_final_project.entity.User;
import kr.adapterz.jpa_final_project.repository.PostRepository;
import kr.adapterz.jpa_final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /** C */
    @Transactional
    public Post create(Long authorId, String title, String content) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("author not found: " + authorId));
        Post post = new Post(title, content, author);
        return postRepository.save(post);
    }

    /** R 단건 */
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("post not found: " + id));
    }

    /** R 동적 조회 (제목/내용/작성자 닉네임) */
    public Page<Post> search(String title, String content, String authorNickname, Pageable pageable) {
        return postRepository.search(title, content, authorNickname, pageable);
    }

    /** U: 부분 업데이트(제목/내용 제공된 값만 변경) */
    @Transactional
    public Post update(Long id, String title, String content) {
        Post post = findById(id); // 영속 엔티티

        if (title != null && !title.isBlank()) post.changeTitle(title.trim());
        if (content != null && !content.isBlank()) post.changeContent(content.trim());

        return post; // Dirty Checking으로 커밋 시 UPDATE
    }

    /** D */
    @Transactional
    public void delete(Long id) {
        postRepository.delete(findById(id));
    }
}
