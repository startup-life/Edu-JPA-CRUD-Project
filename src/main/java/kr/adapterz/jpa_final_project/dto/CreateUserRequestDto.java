package kr.adapterz.jpa_final_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequestDto {
    private String email;
    private String password;
    private String nickname;
}