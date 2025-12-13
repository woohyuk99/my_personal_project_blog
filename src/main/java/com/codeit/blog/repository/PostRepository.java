package com.codeit.blog.repository;

import com.codeit.blog.entity.Category;
import com.codeit.blog.entity.Post;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


// Repository에서 인터페이스 사용하는 이유
// 설계도인 인터페이스 만들면 Spring이 자동으로 구현체 생성


public interface PostRepository extends JpaRepository<Post, Long> { // Long은 Post 엔티티의 pk 타입을 의미.

  // 제목, 내용, 태그 부분 검색
  List<Post> findByTitleContaining(String keyword); // Post 엔티티에 여러 개 담을 예정, keyword 포함한 title 가져오기
  List<Post> findByContentContaining(String keyword);
  List<Post> findByTagsContainingIgnoreCase(String tags);


  // 카테고리별 블로그 찾기
  List<Post> findByCategory(Category category);
  // List라는 바구니에 Post 엔티티를 여러 개 담을 건데, Category라는 enum 조건에 맞는 Post 엔티티의 category 필드를 검색 후 찾아.
  List<Post> findByCategoryIn(Collection<Category> categories);
  // 여러 개 카테고리 중 해당되는 거 가져오기, Collection은 List와 Set 모두 포함

  // 작성자 id로 블로그 찾기
  List<Post> findByAuthor_Id(Long authorId);
  List<Post> findByAuthor_IdOrderByIdDesc(Long authorId);
  List<Post> findByAuthor_NicknameContaining(String nickname);



}
