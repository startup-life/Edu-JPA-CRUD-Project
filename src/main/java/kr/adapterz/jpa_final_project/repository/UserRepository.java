package kr.adapterz.jpa_final_project.repository;

import kr.adapterz.jpa_final_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
