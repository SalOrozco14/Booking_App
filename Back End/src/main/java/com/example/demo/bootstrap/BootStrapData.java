package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository,DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("BootStrapData LOADING....");
        if(customerRepository.count() <= 1) {
            Customer c1 = new Customer("Yohan", "Van Dan", "123 West St", "46584",
                    "555-555-5555", new Date(), new Date(), divisionRepository.getReferenceById(5L));

            Customer c2 = new Customer("Jared", "Smith", "456 Name St", "46584",
                    "555-555-5555", new Date(), new Date(), divisionRepository.getReferenceById(10L));

            Customer c3 = new Customer("Darla", "Mcdonald", "45 Palm St", "46584",
                    "555-555-5555", new Date(), new Date(), divisionRepository.getReferenceById(20L));

            Customer c4 = new Customer("Grace", "Hopper", "55 North St", "46584",
                    "555-555-5555", new Date(), new Date(), divisionRepository.getReferenceById(7L));

            Customer c5 = new Customer("Lady", "Lovelace", "123 Main St", "46584",
                    "555-555-5555", new Date(), new Date(), divisionRepository.getReferenceById(44L));

            customerRepository.save(c1);
            customerRepository.save(c2);
            customerRepository.save(c3);
            customerRepository.save(c4);
            customerRepository.save(c5);

            System.out.println("5 Customers Loaded");

        }else{
            System.out.println("Customers already exist");
        }
    }
}
