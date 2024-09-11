package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve info
        Cart cart = purchase.getCart();
        Customer customer = purchase.getCustomer();
        Set<CartItem> cartItems = purchase.getCartItems();

        //generate a tracking number
        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        //Changing cart status to "ordered"
        cart.setStatus(StatusType.ordered);

        //populate cart with cartItem
        cartItems.forEach(Item -> cart.add(Item));

        //populate customer with cart
        customer.add(cart);

        //save to the database
        customerRepository.save(customer);

        //return a response
        if(cartItems.isEmpty()) {
            return new PurchaseResponse("CART IS EMPTY");
        }else{
            return new PurchaseResponse(orderTrackingNumber);
        }
    }


}
