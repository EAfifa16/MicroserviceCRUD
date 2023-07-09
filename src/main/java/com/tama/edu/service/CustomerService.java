package com.tama.edu.service;

import com.tama.edu.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    ResponseEntity<Customer> createCustomer(Customer customer);

    ResponseEntity<Customer> getCustomerById(String custId);

    ResponseEntity<List<Customer>> findAllCustomer();

    ResponseEntity<Customer> updateCustomer(String custId, Customer customer);

    ResponseEntity<String> deleteCustomer(String custId);

}
