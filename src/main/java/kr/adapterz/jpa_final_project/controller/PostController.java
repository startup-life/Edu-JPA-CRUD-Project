package kr.adapterz.jpa_final_project.controller;

import kr.adapterz.jpa_final_project.dto.CreatePostRequest;
import kr.adapterz.jpa_final_project.dto.PostResponse;
import kr.adapterz.jpa_final_project.dto.UpdatePostRequest;
import kr.adapterz.jpa_final_project.entity.Post;
import kr.adapterz.jpa_final_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /** Create */
    @PostMapping
    public PostResponse create(@RequestBody CreatePostRequest request) {
        Post saved = postService.create(request.getAuthorId(), request.getTitle(), request.getContent());
        return PostResponse.of(saved);
    }

    /** Read - by id */
    @GetMapping("/{id}")
    public PostResponse findOne(@PathVariable Long id) {
        return PostResponse.of(postService.findById(id));
    }

    /** Read - dynamic (title/content/authorNickname) */
    @GetMapping
    public List<PostResponse> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false, name = "author") String authorNickname,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = DESC) Pageable pageable) {
        return postService.search(title, content, authorNickname, pageable).stream()
                .map(PostResponse::of)
                .toList();
    }

    /** Update - 부분 업데이트(제목/내용 제공된 값만) */
    @PatchMapping("/{id}")
    public PostResponse update(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        Post updated = postService.update(id, request.getTitle(), request.getContent());
        return PostResponse.of(updated);
    }

    /** Delete */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
