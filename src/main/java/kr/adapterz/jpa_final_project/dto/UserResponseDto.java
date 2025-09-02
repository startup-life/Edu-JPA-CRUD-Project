package kr.adapterz.jpa_final_project.dto;

import kr.adapterz.jpa_final_project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname());
    }
}