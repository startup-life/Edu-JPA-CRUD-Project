package kr.adapterz.jpa_final_project.repository;

import kr.adapterz.jpa_final_project.entity.Post;
import org.springframework.data.domain.*;

public interface PostRepositoryCustom {
    Page<Post> search(String title, String content, String authorNickname, Pageable pageable);
}
