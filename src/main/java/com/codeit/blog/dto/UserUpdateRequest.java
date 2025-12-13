package com.codeit.blog.dto;

import java.time.LocalDate;

public record UserUpdateRequest(
    // username은 수정 불가능
    String password, // password는 경우에 따라 수정해야 할 수 있음
    String email,
    String nickname,
    LocalDate birthday
) {

}
