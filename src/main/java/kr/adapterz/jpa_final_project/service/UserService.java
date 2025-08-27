package kr.adapterz.jpa_final_project.service;

import kr.adapterz.jpa_final_project.entity.User;
import kr.adapterz.jpa_final_project.repository.PostRepository;
import kr.adapterz.jpa_final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /** C */
    @Transactional
    public User create(String email, String password, String nickname) {
        User user = new User(email, password, nickname);
        return userRepository.save(user);
    }

    /** R 단건 */
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found: " + id));
    }

    /** R 동적 조회 (이메일 / 닉네임) */
    public List<User> search(String email, String nickname) {
        return userRepository.search(email, nickname);
    }

    /** U: 닉네임 / 비밀번호 중 제공된 필드만 변경 (부분 업데이트) */
    @Transactional
    public User update(Long id, String nickname, String password) {
        User user = findById(id); // 영속 엔티티
        if (nickname != null && !nickname.isBlank()) user.changeNickname(nickname);
        if (password != null && !password.isBlank()) user.changePassword(password);
        return user;
    }

    /** D */
    @Transactional
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }
}
