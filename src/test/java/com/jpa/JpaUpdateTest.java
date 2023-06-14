package com.jpa;

import com.jpa.entity.Products;
import com.jpa.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {JpaTestApplication.class})
@Slf4j
public class JpaUpdateTest {

    @Autowired
    ProductsRepository productsRepository;

    /**
     * 更新時會將所有欄位做更新
     */
    @Test
    public void updateTest1() {
        Products products = productsRepository.findById(1).get();
        products.setDescription("書本1");
        productsRepository.save(products);
    }

}
