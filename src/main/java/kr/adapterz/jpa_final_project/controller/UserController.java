package kr.adapterz.jpa_final_project.controller;

import kr.adapterz.jpa_final_project.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import kr.adapterz.jpa_final_project.entity.User;
import kr.adapterz.jpa_final_project.service.UserService;
import kr.adapterz.jpa_final_project.dto.CreateUserRequestDto;
import kr.adapterz.jpa_final_project.dto.UpdateUserRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** Create */
    @PostMapping
    public UserResponseDto create(@RequestBody CreateUserRequestDto request) {
        User saved = userService.create(request.getEmail(), request.getPassword(), request.getNickname());
        return UserResponseDto.of(saved);
    }

    /** Read - by id */
    @GetMapping("/{id}")
    public UserResponseDto findOne(@PathVariable Long id) {
        return UserResponseDto.of(userService.findById(id));
    }

    /** Read - dynamic search (email/nickname) */
    @GetMapping
    public List<UserResponseDto> search(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname
    ) {
        return userService.search(email, nickname).stream()
                .map(UserResponseDto::of)
                .toList();
    }

    /** Update - 부분 업데이트(닉네임/비밀번호 제공된 값만) */
    @PatchMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UpdateUserRequestDto request) {
        User updated = userService.update(id, request.getNickname(), request.getPassword());
        return UserResponseDto.of(updated);
    }

    /** Delete */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}