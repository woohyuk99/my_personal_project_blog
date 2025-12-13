package com.codeit.blog.entity;

import com.codeit.blog.dto.UserUpdateRequest;
import com.codeit.blog.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

// JPA 관련 어노테이션
@Entity
@Table(name = "users") // 실제 db이름은 users

// Lombok 어노테이션
@Getter
@Setter
@SuperBuilder

// StackOverflowError 방지
// 양방향 관계에서 최소 한쪽은 exclude 필요
// 보통 @OneToMany 쪽(User)에서 제외하는 게 일반적
@ToString(exclude = "posts")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // 괄호 안의 필드 무시
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자

public class User extends BaseEntity {

  // BaseEntity에 id가 있기 때문에 해당 코드 필요 없음.
//  @Column(nullable = false, unique = true, length = 100)
//  private static final long id= 1L;

  @Column(nullable = false, length = 100)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false,  length = 100)
  private String email;

  @Column(nullable = false,  length = 100)
  private String nickname;


  @Column(nullable = false)
  private LocalDate birthday;

  @Column(nullable = false)
  private boolean has_avatar;


  @Builder.Default
  @OneToMany(mappedBy = "author") // Post와 author의 연결
  @JsonIgnore // JSON 직렬화 시 무한 참조 방지 : 안 할 경우 User 엔티티에 대한 모든 JSON 정보 발생
  private List<Post> posts = new ArrayList<>(); // 빈 리스트로 초기화 -> 안 할 경우 NullPointerException 발생


  public void update(UserUpdateRequest request){
    if(request.password() != null && !request.password().isEmpty()){
      this.password = request.password();
    }

    if(request.email() != null && !request.email().isEmpty()){
      this.email = request.email();
    }

    if(request.nickname() != null && !request.nickname().isEmpty()){
      this.nickname = request.nickname();
    }

    if(request.birthday() != null){
      this.birthday = request.birthday();
    }
  }

}
