package com.tama.edu.controller;

import com.tama.edu.model.Customer;
import com.tama.edu.service.CustomerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    int retryCount = 1;

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/findAllCustomer")
    public ResponseEntity<List<Customer>> findAllCustomer() {
        return customerService.findAllCustomer();
    }


    /*
     * @CircuitBreaker : if the other service is down it will open the circuit, and call the fallback method.
     * @Retry :          no. of call it will make if the other service is down and then call the fallback method.
     * @RateLimiter :    to limit the no. of users trying to access the service
     */
    @GetMapping("/getCustomerById/{custId}")
//    @CircuitBreaker(name = "hotelServiceBreaker", fallbackMethod = "hotelServiceFallback")
//    @Retry(name = "hotelServiceRetry", fallbackMethod = "hotelServiceFallback")
    @RateLimiter(name = "customerRateLimiter", fallbackMethod = "hotelServiceFallback")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String custId) {
        retryCount++;
        return customerService.getCustomerById(custId);
    }

    //fall back method for circuit-breaker
    public ResponseEntity<Customer> hotelServiceFallback(String custId, Exception exp) {
        Customer cust = new Customer();
        cust.setCustAddress("Dummy");
        cust.setCustName("Dummy");
        cust.setCustId(custId);
        return new ResponseEntity<>(cust, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer/{custId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String custId, @RequestBody Customer customer) {
        return customerService.updateCustomer(custId, customer);
    }

    @DeleteMapping("/deleteCustomer/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String custId) {
        return customerService.deleteCustomer(custId);
    }
}
