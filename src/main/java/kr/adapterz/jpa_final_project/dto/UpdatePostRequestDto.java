package kr.adapterz.jpa_final_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostRequestDto {
    private String title;   // optional
    private String content; // optional
}