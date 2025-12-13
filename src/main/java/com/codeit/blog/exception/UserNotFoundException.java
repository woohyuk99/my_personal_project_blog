package com.codeit.blog.exception;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(Long id) {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String username) {
    super(ErrorCode.USER_NOT_FOUND);
  }


}
