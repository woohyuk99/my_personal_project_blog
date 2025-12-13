package com.codeit.blog.dto;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// response -> 데이터 주기 전용
// 수정 가능 -> class 사용


// 4개의 어노테이션 dto 생성 시 항상 필요(암기)
@Data // Getter와 Setter
@Builder // 객체 생성 편리하게
@AllArgsConstructor // 빈 객체 만드려면 필요
@NoArgsConstructor // 빌더 쓰려면 필요


public class UserResponse {

  private Long id;
  private String username;
  // private String password
  // dto의 response의 경우 보안상 비밀번호와 같은 민감한 정보 절대로 포함x

  private String email;
  private String nickname;
  private boolean hasAvatar;
  private Instant createdAt;
  private Instant updatedAt;


}
