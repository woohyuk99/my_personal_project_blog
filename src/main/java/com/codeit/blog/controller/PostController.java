package com.codeit.blog.controller;


import com.codeit.blog.dto.PostCreateRequest;
import com.codeit.blog.dto.PostResponse;
import com.codeit.blog.dto.PostUpdateRequest;
import com.codeit.blog.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@RestController // 모든 응답을 JSON 형태로 반환
@RequiredArgsConstructor // 생성자 자동 생성 + 의존성 주입
@RequestMapping("/api/posts") // client의 요청 주소

// 중요! PostController와 PostViewController 의 url 주소 모두 일치해야 함.

public class PostController {
  private final PostService postService; // Controller가 Service에 요청 전달

  @PostMapping // 정보 생성
  public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {
    // ResponseEntity : 스프링이 무료로 제공하는 HTTP 응답 전용 포장 박스
    // @RequestBody: Json -> DTO
    // 내용 추가 예정

    PostResponse created = postService.create(postCreateRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping("/{id}") // 단건 조회
  public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
    // 1. PostService에게 id로 조회 요청
    PostResponse response = postService.findById(id);

    // 2. 결과 반환 -> 200 ok
    return ResponseEntity.ok(response);
  }

  @GetMapping // 전체 조회
  public ResponseEntity<List<PostResponse>> getAllPosts(){
  //id 조회 요청 -> findAllPosts 이용 (Mapper가 List로 자동 변환)
    List<PostResponse> responses  = postService.findAllPosts();
    return ResponseEntity.ok(responses);
  }

  @PutMapping("{id}") // 수정
  public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
    PostResponse updated = postService.update(id, postUpdateRequest);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("{id}")  // 삭제
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
