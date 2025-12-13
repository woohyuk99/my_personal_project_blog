package com.codeit.blog.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends BusinessException {


  public PostNotFoundException(Long id) {
    super(ErrorCode.POST_NOT_FOUND);
  }
}
