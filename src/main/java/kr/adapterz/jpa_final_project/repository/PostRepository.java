package kr.adapterz.jpa_final_project.repository;

import kr.adapterz.jpa_final_project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
