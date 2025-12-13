package com.codeit.blog.dto;

public record PostUpdateRequest(

    String title,
    String content,
    String tags,
    String category


) {

}
