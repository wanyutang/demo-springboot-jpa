package com.jpa;

import com.jpa.entity.Users;
import com.jpa.repository.UsersRepository;
import com.jpa.specification.AdminRequest;
import com.jpa.specification.UserRequest;
import com.jpa.specification.UsersSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;

@SpringBootTest(classes = {JpaTestApplication.class})
@Slf4j
public class JpaSpecificationQueryTest {

    @Autowired
    UsersSpecification usersSpecification;

    @Autowired
    UsersRepository usersRepository;

    /**
     * 自定義查詢條件
     * 查詢條件都為 null 時，where 自帶 1=1
     */
    @Test
    public void testQueryParamsIsNull() {
        Specification<Users> specification =
                usersSpecification.findUsersByUserRequest(UserRequest.builder().build());

        usersRepository.findAll(specification).forEach(users -> {
           log.info("name: {}, email: {}", users.getUsername(), users.getEmail());
        });

    }

    /**
     * 動態查詢條件
     */
    @Test
    public void testQueryDynamic() {
        Specification<Users> specification =
                usersSpecification.findUsersByUserRequest(UserRequest.builder()
                                .username("jason").build());

        usersRepository.findAll(specification).forEach(users -> {
            log.info("name: {}, email: {}", users.getUsername(), users.getEmail());
        });
    }

    /**
     * 針對 join table 當作查詢條件
     *
     */
    @Test
    @Transactional
    public void testQueryJoin() {
        Specification<Users> specification =
                usersSpecification.findUsersByAdminRequest(AdminRequest.builder()
                        .user_id(1).build());

        usersRepository.findAll(specification).forEach(users -> {
            log.info("name: {}, email: {}", users.getUsername(), users.getEmail());
            users.getShoppingCarts().forEach(shoppingCarts -> {
                log.info("11");
            });
        });
    }

}
