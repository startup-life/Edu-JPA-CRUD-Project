package kr.adapterz.jpa_final_project.repository;

import kr.adapterz.jpa_final_project.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> search(String email, String nickname);
}
