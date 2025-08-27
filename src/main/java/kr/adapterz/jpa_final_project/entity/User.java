package kr.adapterz.jpa_final_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Getter
@RequiredArgsConstructor
public class User extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;

    /**
     * JPA 매핑에 고아 객체 삭제 + REMOVE cascade (부모 지우면 자식 자동 삭제)
     * User 엔티티에 컬렉션 쪽 옵션을 켜면, 컬렉션에서 제거되거나 부모 삭제 시 자식도 삭제됩니다.
     */
    @OneToMany(mappedBy = "author",
            cascade = REMOVE,
            orphanRemoval = true)
    List<Post> posts = new ArrayList<>();

    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
