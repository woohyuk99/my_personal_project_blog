package com.codeit.blog.repository;

import com.codeit.blog.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

  // Repository 는 일반적으로 인터페이스 사용 -> Spring이 자동으로 구현체 생성

  // username으로 사용자 찾기
  Optional<User> findByUsername(String username);

  // email로 사용자 찾기
  Optional<User> findByEmail(String email);

  // username 중복 체크
  boolean existsByUsername(String username);

  // email 중복 체크
  boolean existsByEmail(String email);

}
