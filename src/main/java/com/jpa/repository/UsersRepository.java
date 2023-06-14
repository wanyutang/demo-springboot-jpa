package com.jpa.repository;

import com.jpa.entity.Users;
import com.jpa.entity.model.UserWithCartId;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    @Query(value = "select a.user_id, a.username, b.cart_id from Users a " +
                    "inner join Shopping_Carts b on a.user_id = b.user_id",
        nativeQuery = true)
    public List<UserWithCartId> findHappy();

}