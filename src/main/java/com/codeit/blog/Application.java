package com.codeit.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories // 메인 클래스에서 Auditing 기능 활성화 -> 자동으로 생성.수정 시간 기록 (created_at, updated_at

public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args); // spring boot 안의 embedded tomcat 실행
    System.out.println("http://localhost:8080/");
  }
}
