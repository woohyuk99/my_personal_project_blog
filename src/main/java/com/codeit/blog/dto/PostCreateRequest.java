package com.codeit.blog.dto;

//CreateRequest -> request는 데이터 받기 전용
// 어노테이션 일단 필요x
// 수정 불필요 -> record는 한 번 적으면 끝

import com.codeit.blog.entity.Category;
import com.codeit.blog.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
    // id는 db의 영역 -> post 생성할 때 id 모름, 이 때 임의로 id 넣으면 db에서 중복 에러 발생
    // 은행 번호표 생각하면 됨.

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
    String title,

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 5000, message = "내용은 5000자를 초과할 수 없습니다.")
    String content,

    @NotBlank(message = "태그는 필수입니다.")
    String tags,

    @NotBlank(message = "카테고리는 필수입니다.")
    String category,

    @NotNull(message = "작성자 ID는 필수입니다.")
    Long authorId // 게시글 생성 시 작성자 지정

//    dto에서 db와 직접 연결된 entity 객체 넣으면 안 됨.
//    Category category,
//    User author

) {
}
