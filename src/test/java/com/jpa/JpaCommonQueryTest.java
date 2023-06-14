package com.jpa;

import com.jpa.entity.CartItems;
import com.jpa.entity.Products;
import com.jpa.repository.CartItemsRepository;
import com.jpa.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = {JpaTestApplication.class})
@Slf4j
public class JpaCommonQueryTest {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    /**
     * 一般使用 tag(OneToMany) 方式將關聯式資料拉出
     * 使用 LAZY 設定須設定 @Transactional 否則會出現 no session 情況
     *
     */
    @Test
    @Transactional
    public void testQuery() {
        List<Products> products = productsRepository.findAll();
        log.info("products size:　" + products.size());

        for (Products product : products) {
            for (CartItems cartItem : product.getCartItems()) {
                log.info("Product id: {}, User id: {}", cartItem.getProduct_id(), cartItem.getShoppingCarts().getUser_id());
            }
        }
    }

    @Test
    @Transactional
    public void testQuery2() {
        cartItemsRepository.findAll()
                .forEach(cartItems -> {
                    log.info("name: {}", cartItems.getProducts().getProduct_name());
                });
    }

    /**
     * 使用 lambda 不影響 LAZY
     */
    @Test
    @Transactional
    public void testQuery3() {
        productsRepository.findAll().forEach(products -> {
            products.getCartItems().forEach(cartItems -> {
                log.info("Product id: {}, User id: {}", cartItems.getProduct_id(), cartItems.getShoppingCarts().getUser_id());
            });
        });
    }

}
