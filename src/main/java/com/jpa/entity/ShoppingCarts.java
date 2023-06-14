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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Shopping_Carts")
public class ShoppingCarts {

    @Id
    @Column(name = "cart_id")
    private int cart_id;
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "created_at")
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Users users;

    @OneToMany
    private List<CartItems> cartItems;
}
