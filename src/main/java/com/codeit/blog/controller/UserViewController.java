package com.codeit.blog.controller;


import com.codeit.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 사용자 브라우저 접속 시 눈에 보이는 html 화면 제공
// 중요! UserController와 UserViewController 의 url 주소 모두 일치해야 함.


@Controller

public class UserViewController {

  @GetMapping("/user/signup") // 회원가입
  public String signup(){
    return "user/signup";
  }

  @GetMapping("/user/update") // 회원수정
  public String update(){
    return "user/update";
  }

  @GetMapping("/user/delete") // 회원탈퇴
  public String delete(){
    return "user/delete";
  }


  @GetMapping("/user/login") // 로그인
  public String login(){
    return "user/login";
  }

  @GetMapping("/user/find") // 회원조회 : 단건 조회
  public String find(){
    return "user/find";
  }

  @GetMapping("/user/list") // 회원조회: 전체 조회
  public String list(){ return "user/list"; }


}
