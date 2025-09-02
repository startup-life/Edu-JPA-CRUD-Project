package kr.adapterz.jpa_final_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequestDto {
    private Long authorId;
    private String title;
    private String content;
}