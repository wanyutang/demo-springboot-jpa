package com.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Cart_Items")
public class CartItems {

    @Id
    @Column(name = "item_id")
    private int item_id;
    @Column(name = "cart_id")
    private int cart_id;
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private ShoppingCarts shoppingCarts;
}
