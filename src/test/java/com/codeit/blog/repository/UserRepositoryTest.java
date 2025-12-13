package com.codeit.blog.repository;


import com.codeit.blog.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

//@SpringBootTest // spring 전체 load하는 어노테이션
@DataJpaTest // jpa에 관련된 것들만 load하는 어노테이션
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager em; // 트랜잭션 컨트롤 manager

    @Test
    @DisplayName("findAll test")
    void findAllTest() {
        List<User> list = userRepository.findAll();
        assert !list.isEmpty();
        list.forEach(System.out::println);

    }


}
