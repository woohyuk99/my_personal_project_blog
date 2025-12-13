package com.codeit.blog.entity;

import com.codeit.blog.dto.PostUpdateRequest;
import com.codeit.blog.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "posts")
@ToString(exclude = "author")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer" ,"handler"})




public class Post extends BaseEntity {

//  @Column(nullable = false, unique = true, length = 100)
//  private String id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, length = 100)
  private String content;

  @Column(nullable = false, length = 100)
  private String tags; // 핵심 키워드 자유롭게 달기 : ex) #맛집, #꿀팁

  @Enumerated(EnumType.STRING) // 열거형(enum) 타입 db에 저장할 때 쓰는 어노테이션
  @Column(nullable = false, length = 100)
  private Category category; // 게시글의 큰 주제를 나누는 기능 : ex) [TECHNOLOGY], [LIFESTYLE]

  // join 요소
  @ManyToOne(fetch = FetchType.LAZY) // 다대다 관계, 프록시 서버 이용한 지연 로딩
  @JoinColumn(name = "author_id", nullable = false) // db의 외래키 'authorId'를 가져와서 2개의 엔티티를 연결, null값 x
  private User author; // Post와 User를 다대일 관계를 잇는 연결 고리 역할. posts 테이블의 authorId와 매핑

  public void update(PostUpdateRequest request){
    if(request.title() != null && !request.title().isEmpty()){
      this.title = request.title();
    }

    if(request.content() != null && !request.content().isEmpty()){
      this.content = request.content();
    }

    if(request.tags() != null && !request.tags().isEmpty()){
      this.tags = request.tags();
    }
  }
//  private int authorId;
//  private Date createdPosts;
//  private Date updatedPosts;
}
