package com.codeit.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // HTML 파일 반환 -> 보여주기 용

// 중요! PostController와 PostViewController 의 url 주소 모두 일치해야 함.


public class PostViewController { // 화면 보여주기 용도

  // 1. create
  @GetMapping("/post/create") // 게시글 생성 + 기능 수행 페이지로 이동
  public String create() { return "post/create"; }

  // 2. delete
  @GetMapping("/post/delete") // 게시글 삭제
  public String delete() { return "post/delete"; }

  // 3. find
  @GetMapping("/post/find") // 게시글 단건 조회
  public String find() { return "post/find"; }

  @GetMapping("/post/list") // 게시글 전체 조회
  public String list() { return "post/list"; }

  // 4. update
  @GetMapping("/post/update") // 게시글 수정
  public String update() { return "post/update"; }

}
