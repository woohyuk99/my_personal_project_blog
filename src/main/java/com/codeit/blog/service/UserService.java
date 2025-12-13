package com.codeit.blog.service;


import com.codeit.blog.dto.UserCreateRequest;
import com.codeit.blog.dto.UserResponse;
import com.codeit.blog.dto.UserUpdateRequest;
import com.codeit.blog.entity.User;
import com.codeit.blog.exception.BusinessException;
import com.codeit.blog.exception.ErrorCode;
import com.codeit.blog.exception.UserNotFoundException;
import com.codeit.blog.mapper.UserMapper;
import com.codeit.blog.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor



public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserResponse create(UserCreateRequest request){
    if (userRepository.existsByEmail(request.email())) {
      throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
    }

    User user = userMapper.toUser(request);
    User savedUser = userRepository.save(user);
    return userMapper.toUserResponse(savedUser);
  }


  @Transactional(readOnly = true)

  public UserResponse findById(Long id){
    User user = userRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException(id));
    return userMapper.toUserResponse(user);
  }

  @Transactional(readOnly = true)

  public List<UserResponse> findAllUsers() {
    List<User> users = userRepository.findAll();
    return userMapper.toUserResponseList(users);
  }


  @Transactional
  public UserResponse update(Long id, UserUpdateRequest request){
    User user = userRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException(id));
    user.update(request);
    return userMapper.toUserResponse(user);
  }



  @Transactional
  public void delete(Long id){
    User user = userRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException(id));
    userRepository.delete(user);
  }

  @Transactional(readOnly = true)
  public UserResponse findByUsername(String username){
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(username));
    return userMapper.toUserResponse(user);
  }
}
