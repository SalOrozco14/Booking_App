package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @NotBlank(message ="FIRST NAME MANDATORY")
    @Column(name="customer_first_name",nullable = false)
    private String firstName;

    @NotBlank(message ="LAST NAME MANDATORY")
    @Column(name="customer_last_name",nullable = false)
    private String lastName;

    @NotBlank(message ="ADDRESS MANDATORY")
    @Column(name="address",nullable = false)
    private String address;

    @NotBlank(message ="POSTAL CODE MANDATORY")
    @Column(name="postal_code",nullable = false)
    private String postal_code;

    @NotBlank(message ="PHONE MANDATORY")
    @Column(name="phone",nullable = false)
    private String phone;

    @Column(name="create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name="last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Cart> carts = new HashSet<>();

    //Constructors
    public Customer() {
    }

    public Customer(String firstName, String lastName, String address, String postal_code, String phone, Date create_date, Date last_update, Division division) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.last_update = last_update;
        this.division = division;
    }

    public void add(Cart cart) {
        if(cart != null){

            if(carts == null){
                carts= new HashSet<>();
            }

            carts.add(cart);
            cart.setCustomer(this);
        }
    }
}
