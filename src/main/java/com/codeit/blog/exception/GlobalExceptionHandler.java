package com.codeit.blog.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException e) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", 404);
    body.put("error", "Not Found");
    body.put("message", e.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", 404);
    body.put("error", "Not Found");
    body.put("message", e.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

}
