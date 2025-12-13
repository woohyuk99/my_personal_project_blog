package com.codeit.blog.mapper;

import com.codeit.blog.dto.PostCreateRequest;
import com.codeit.blog.dto.PostResponse;
import com.codeit.blog.entity.Post;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

// mapstruct 자동 코드 변환, 모델 위에 spring bean 등록 + 타겟 객체(User)에는 있지만 소스 객체(Request)에 없는 필드가 있어도, 경고 띄우지 말고 그냥 넘어가라(Ignore)"는 뜻입니다.
// mapper = 설계도, mapstruct = 기계, @Mapper = 작동 스위치
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PostMapper { // 설계도 작성

  // mapper의 역할: dto와 entity를 서로 변환
  // @Mapping에서 source와 target의 차이점: from / to


  @Mapping(target = "author", ignore = true) // dto의 authorId와 entity의 author 타입이 서로 다름. 따라서 무시.
  @Mapping(target = "id", ignore = true) // 현재 dto에 id 없음. entity가 db 저장될 때 자동 생성. 따라서 현재는 id 무시
  Post toPost(PostCreateRequest request); // dto -> entity


// source는 Post를 보고, target은 PostResponse를 본다.
  @Mapping(source = "author.nickname", target = "username")
  @Mapping(source = "author.id", target = "authorId")
  PostResponse toPostResponse(Post post); // entity -> dto

  List<PostResponse> toPostResponseList(List<Post> posts);
}


// 1. dto -> entity
// 2. entity -> dto
// 3. entity 집합 -> dto 집합