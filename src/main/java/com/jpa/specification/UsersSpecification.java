package com.jpa.specification;

import com.jpa.entity.ShoppingCarts;
import com.jpa.entity.Users;
import com.microsoft.sqlserver.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersSpecification {

    public Specification<Users> findUsersByUserRequest(UserRequest userRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(!StringUtils.isEmpty(userRequest.getUsername())) {
                predicates.add(criteriaBuilder.equal(root.get("username"),
                        userRequest.getUsername()));
            }

            if(!StringUtils.isEmpty(userRequest.getEmail())) {
                predicates.add(criteriaBuilder.equal(root.get("email"),
                        userRequest.getEmail()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Users> findUsersByAdminRequest(AdminRequest adminRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(!StringUtils.isEmpty(adminRequest.getUsername())) {
                predicates.add(criteriaBuilder.equal(root.get("username"),
                        adminRequest.getUsername()));
            }

            if(adminRequest.getUser_id() != null) {
                Join<ShoppingCarts, Users> join = root.join("shoppingCarts");
                predicates.add(criteriaBuilder.equal(join.get("user_id"),
                        adminRequest.getUser_id()));

            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
