package kr.adapterz.jpa_final_project.dto;

import kr.adapterz.jpa_final_project.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorNickname;

    public static PostResponseDto of(Post p) {
        return new PostResponseDto(
                p.getId(),
                p.getTitle(),
                p.getContent(),
                p.getAuthor() != null ? p.getAuthor().getId() : null,
                p.getAuthor() != null ? p.getAuthor().getNickname() : null
        );
    }
}