package com.codeit.blog.dto;


// Record는 데이터 한 번 보내면 끝 -> Request(주문서)에 잘 맞음
// Class는 데이터 수정 및 기능 확장, 자바의 오래된 규칙 따름
// 따라서 Response(결과물)에 적합함

import com.codeit.blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 4개 어노테이션 dto 생성 시 항상 필요(암기)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class PostResponse { // 사용자에게 게시글의 어떤 정보를 보여줄 것인가?
  // dto의 response의 경우 보안상 비밀번호와 같은 민감한 정보 절대로 포함x
  private Long id;
  private String title;
  private String content;
  private String tags;
  private String category;
  private String username; // 작성자 닉네임
  private Long authorId; // 작성자 ID
  // 보안상 이유로 entity, enum 그대로 넣으면 안 됨.
//  private Category category;
//  private User author;

}
