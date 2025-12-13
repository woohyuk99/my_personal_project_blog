package com.codeit.blog.service;
// 서비스의 역할 = crud 기능 구현 + dto <-> entity 변환하기

import com.codeit.blog.dto.PostCreateRequest;
import com.codeit.blog.dto.PostResponse;
import com.codeit.blog.dto.PostUpdateRequest;
import com.codeit.blog.entity.Post;
import com.codeit.blog.entity.User;
import com.codeit.blog.exception.BusinessException;
import com.codeit.blog.exception.ErrorCode;
import com.codeit.blog.exception.PostNotFoundException;
import com.codeit.blog.exception.UserNotFoundException;
import com.codeit.blog.mapper.PostMapper;
import com.codeit.blog.repository.PostRepository;
import com.codeit.blog.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 비즈니스 로직 담당하는 CRUD bean
@RequiredArgsConstructor // 의존성 주입 + 생성자 자동 생성


public class PostService {

  // 1. Repository, mapper 가져오기
  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final UserRepository userRepository;

  // 2. CRUD 기능 구현
  @Transactional // All or nothing
  public PostResponse create(PostCreateRequest request){
    Post post = postMapper.toPost(request); // dto -> entity 변환

    // TroubleShooting : authorId를 찾아서 Post에 연결해주는 코드가 현재 아예 없음. -> PostService에 해당 기능을 구현하는 코드 추가하기.
    // 잘못된 코드: Long authorId = post.getAuthor().getId(); // 현재 getAuthor의 상태 null -> getId도 null

    Long authorId = request.authorId();

    //User 정보 찾기 -> Repository가 해당 숫자로 User 객체 찾아오기
    User user =  userRepository.findById(authorId)
          .orElseThrow(()-> new UserNotFoundException(authorId));

    // 게시글에 작성자 번호 입력
    post.setAuthor(user);


    // 요구사항 구현, pk에 해당하는 값이 무엇인가? -> id
    if(post.getId() != null){ // id 값이 벌써 존재?
        throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
    }

    // try-catch문 제거 (Spring에서 직접 Exception Handler)
    Post savedPost = postRepository.save(post);
    return postMapper.toPostResponse(savedPost);

  }

  @Transactional(readOnly = true) // 데이터 변경x, 읽기 전용
  public PostResponse findById(Long id) { // id 기반으로 찾기 + findById는 사용자에게 정보를 보여주는 기능으로 response 써야 함.
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(id));
    return postMapper.toPostResponse(post); // entity -> dto
  }

  @Transactional(readOnly = true)
  public List<PostResponse> findAllPosts() {
    List<Post> posts = postRepository.findAll();
    return postMapper.toPostResponseList(posts);
  }

  @Transactional // update
  public PostResponse update(Long id , PostUpdateRequest request){
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(id));

    post.update(request);
    return postMapper.toPostResponse(post);
  }

  @Transactional // delete
  public void delete(Long id) {
    Post post = postRepository.findById(id)

    .orElseThrow(() -> new PostNotFoundException(id));
    postRepository.delete(post);

  }
}
