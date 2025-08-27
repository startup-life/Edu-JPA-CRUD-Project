package kr.adapterz.jpa_final_project.controller;

import kr.adapterz.jpa_final_project.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import kr.adapterz.jpa_final_project.entity.User;
import kr.adapterz.jpa_final_project.service.UserService;
import kr.adapterz.jpa_final_project.dto.CreateUserRequest;
import kr.adapterz.jpa_final_project.dto.UpdateUserRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** Create */
    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request) {
        User saved = userService.create(request.getEmail(), request.getPassword(), request.getNickname());
        return UserResponse.of(saved);
    }

    /** Read - by id */
    @GetMapping("/{id}")
    public UserResponse findOne(@PathVariable Long id) {
        return UserResponse.of(userService.findById(id));
    }

    /** Read - dynamic search (email/nickname) */
    @GetMapping
    public List<UserResponse> search(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname
    ) {
        return userService.search(email, nickname).stream()
                .map(UserResponse::of)
                .toList();
    }

    /** Update - 부분 업데이트(닉네임/비밀번호 제공된 값만) */
    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        User updated = userService.update(id, request.getNickname(), request.getPassword());
        return UserResponse.of(updated);
    }

    /** Delete */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}