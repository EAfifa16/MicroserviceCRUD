package com.tama.edu.service;

import com.tama.edu.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    public ResponseEntity<Customer> createCustomer(Customer customer);

    public ResponseEntity<Customer> getCustomerById(String custId);

    public ResponseEntity<List<Customer>> findAllCustomer();

    ResponseEntity<Customer> updateCustomer(String custId, Customer customer);

    public ResponseEntity<String> deleteCustomer(String custId);

}
