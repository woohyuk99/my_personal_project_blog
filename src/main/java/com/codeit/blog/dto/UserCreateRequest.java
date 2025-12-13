package com.codeit.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// request -> 데이터 받기 전용
// 수정할 필요 없음 -> record 사용 ("한 번 적으면 끝!")
// 유효성 검사 어노테이션은 나중에 필요할 때 사용
// 요청(주문서) 이기 때문에 비밀번호 포함

public record UserCreateRequest(
    // id는 db가 알아서 해주므로 넣을 필요 없음.

    @NotBlank(message = "사용자 이름은 필수입니다.")
    @Size(min = 4, max = 20, message = "사용자 이름은 4~20자 이어야 합니다.")
    String username, // 이후 수정 불가능

    @NotNull(message = "비밀번호는 필수입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    String password,

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotBlank(message = "닉네임은 필수입니다.")
    String nickname,

    @NotNull(message = "생년월일은 필수입니다.")
    @Past(message = "생년월일은 과거 날짜입니다.")
    LocalDate birthday
)
{}
