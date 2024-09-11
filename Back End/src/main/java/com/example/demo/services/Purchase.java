package com.example.demo.services;


import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class Purchase {

    private Customer customer;

    private Cart cart;

    private Set<CartItem> cartItems;
}
