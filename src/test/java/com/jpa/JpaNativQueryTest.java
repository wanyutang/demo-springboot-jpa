package com.jpa;

import com.jpa.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {JpaTestApplication.class})
@Slf4j
public class JpaNativQueryTest {

    @Autowired
    UsersRepository usersRepository;

    /**
     * NativQuery 回傳自定義封裝物件
     *
     */
    @Test
    public void testquery1() {
        usersRepository.findHappy().forEach(userWithCartId -> {
            log.info("username: {}, cartId: {}", userWithCartId.getUsername(), userWithCartId.getCart_id());
        });
    }

}
