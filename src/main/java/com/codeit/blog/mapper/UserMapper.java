package com.codeit.blog.mapper;


import com.codeit.blog.dto.UserCreateRequest;
import com.codeit.blog.dto.UserResponse;
import com.codeit.blog.entity.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// mapstruct 자동 코드 변환, 모델 위에 spring bean 등록 + 타겟 객체(User)에는 있지만 소스 객체(Request)에 없는 필드가 있어도, 경고 띄우지 말고 그냥 넘어가라(Ignore)"는 뜻입니다.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserMapper { // 설계도 작성
  User toUser(UserCreateRequest request); // UserCreateRequest dto를 User 엔티티로 변환
  UserResponse toUserResponse(User user); // User 엔티티를 UserResponse dto로 변환
  List<UserResponse> toUserResponseList(List<User> users); // List User 엔티티를 List UserResponse dto로 변환

  // 1. dto -> entity
  // 2. entity -> dto
  // 3. entity 집합 -> dto 집합
}
