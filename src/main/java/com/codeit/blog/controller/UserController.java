package com.codeit.blog.controller;


import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.codeit.blog.dto.UserCreateRequest;
import com.codeit.blog.dto.UserResponse;
import com.codeit.blog.dto.UserUpdateRequest;
import com.codeit.blog.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // rest architecture style을 지키는 웹(HTTP) 통신을 하는 api 창구 -> JSON 데이터 반환
@RequiredArgsConstructor // 생성자 자동 생성
@RequestMapping("/api/users") // 이 클래스의 모든 주소는 api/users로 시작, url과 코드의 연결

// 중요! UserController와 UserViewController 의 url 주소 모두 일치해야 함.


public class UserController {
  private final UserService userService; // 필드 선언

  @PostMapping // 정보 생성(create)
  public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest user) { // @Valid : 검사 시작, 제약 조건 실제 실행.
    UserResponse created = userService.create(user); // UserService는 non static 메소드. 미리 선언한 userService 필드 변수를 사용.
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }


  @GetMapping("/{id}") // 정보 단건 조회
  public ResponseEntity<UserResponse> getUser(@PathVariable Long id){

    // 1. UserService에게 id로 조회 요청
    UserResponse response = userService.findById(id);

    // 2. 결과 반환 -> 200 ok
    return ResponseEntity.ok(response);
  }


  @GetMapping// 정보 전체 조회
  public ResponseEntity<List<UserResponse>> getAllUsers(){
    // 1. UserService에게 id로 조회 요청 -> findAllUsers() 이용 (Mapper가 List로 자동 변환)
    List<UserResponse> responses  = userService.findAllUsers();
    return ResponseEntity.ok(responses);
  }

  @PutMapping("/{id}") // 정보 수정(update)
  public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest){
    UserResponse updated = userService.update(id, userUpdateRequest);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}") // 정보 삭제
  public ResponseEntity<Void> delete(@PathVariable Long id){ // 내용 없기 때문에 void 사용
    userService.delete(id);
    return ResponseEntity.noContent().build(); // 내용 없음(204)
  }


}
