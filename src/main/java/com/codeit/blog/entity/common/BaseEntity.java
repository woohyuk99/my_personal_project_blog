package com.codeit.blog.entity.common;

import com.codeit.blog.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass // 부모 역할 클래스
@EntityListeners(AuditingEntityListener.class) // 엔티티 감시하기 -> 데이터 생성 및 수정 포착
@Getter // 필드값 가져오기
@SuperBuilder // 부모 필드 포함 빌더 패턴
@ToString // 객체 정보 문자열
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성자 자동 생성,
@AllArgsConstructor



public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id;


  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Instant createdAt;


  @LastModifiedDate
  @Column(nullable = false)
  private Instant updatedAt;
}
