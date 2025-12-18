package com.codeit.blog.entity;

import com.codeit.blog.dto.UserUpdateRequest;
import com.codeit.blog.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

  // OAuth2 사용자는 password 없을 수 있음
  @Column(nullable = true, length = 100)
  private String password;

  @Column(nullable = false,  length = 100)
  private String email;

  @Column(nullable = false,  length = 100)
  private String nickname;


  @Column(nullable = false)
  private LocalDate birthday;

  @Column(nullable = false)
  private boolean has_avatar;


  // ========== OAuth2 관련 필드 추가 ==========
  // Enum 값을 db에 저장할 때 문자열로 저장.
  @Enumerated(EnumType.STRING)

  @Column(nullable = false, length = 100)

  // Builder로 객체를 만들 때 기본값(Local) 사용.
  @Builder.Default

  // AuthProvider : 어느 경로에서 왔는지 파악, 초기값은 일반 로그인(Local)으로 간주
  private AuthProvider provider = AuthProvider.LOCAL;

  // ======= OAuth2 provider의 고유 ID (Google: sub, Github: id) ========
  @Column(length = 100)

  // 소셜 로그인 id -> Google, Github에서 사용하는 고유 id
  // 일반 회원 -> providerId 없기 때문에 Null 허용.
  private String providerId;

  @Enumerated(EnumType.STRING)

  @Column(length = 100)
  @Builder.Default

  // 권한 -> 일반 유저
  private Role role =  Role.USER;

  // ======== 이메일 인증 여부 (OAuth2는 자동으로 true) =========
  @Column(nullable = false)
  @Builder.Default

  // 방어적 설계 -> 검증 전까지 false 가정
  private boolean emailVerified = false;





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
