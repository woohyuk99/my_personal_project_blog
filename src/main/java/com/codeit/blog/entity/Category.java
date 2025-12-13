package com.codeit.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor


// category를 enum으로 만든 이유 -> 데이터 오염 방지, 코드 안전 장치
public enum Category { // enum Category, 상수의 집합을 나타내는 특수한 클래스


  // 상수
  TECHNOLOGY("기술", "프로그래밍, IT, 소프트웨어 관련"),
  LIFESTYLE("라이프스타일", "일상, 취미, 자기계발"),
  BUSINESS("비즈니스", "경영, 마케팅, 창업"),
  EDUCATION("교육", "학습, 강의"),
  ENTERTAINMENT("엔터테인먼트", "영화, 음악");


  // 필드, 열거형 상수의 고유 속성
  private final String displayName;
  private final String description;
}
