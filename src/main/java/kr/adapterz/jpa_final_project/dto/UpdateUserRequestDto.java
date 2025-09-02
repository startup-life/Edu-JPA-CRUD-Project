package kr.adapterz.jpa_final_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequestDto {
    private String nickname;  // optional
    private String password;  // optional
}