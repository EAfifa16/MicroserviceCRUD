package com.tama.edu.service;

import com.tama.edu.dao.CustomerRepo;
import com.tama.edu.exception.ResourceNotFoundException;
import com.tama.edu.externalService.HotelService;
import com.tama.edu.model.Customer;
import com.tama.edu.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

//    @Autowired
//    RestTemplate restTemplate;
    
    @Autowired
    HotelService hotelService;

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        customer.setCustId(UUID.randomUUID().toString());
        Customer savedCustomer = customerRepo.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(String custId) {
        Customer customer = customerRepo.findById(custId)
                .orElseThrow(() -> new ResourceNotFoundException("user with given :" + custId + " not present"));

        /*getting hotel-details from hotel using restTemplate- microservice call*/
//        ArrayList<Hotel> hotelDetails = restTemplate.getForObject("http://HOTEL-MICROSERVICE/hotel/getHotelById/"+custId,
//                ArrayList.class);

        /*getting hotel-details from hotel using feignClient- microservice call*/
        List<Hotel> hotelDetails = hotelService.getHotelById(custId);

        customer.setHotel(hotelDetails);

        return ResponseEntity.ok(customer);
    }

    @Override
    public ResponseEntity<List<Customer>> findAllCustomer() {
        List<Customer> allCustomer = customerRepo.findAll();
        return new ResponseEntity<>(allCustomer, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(String custId, Customer customer) {
        Customer cust = null;
        Customer custById = customerRepo.getById(custId);
        if (custById != null) {
            custById.setCustAddress(customer.getCustAddress());
            cust = customerRepo.save(custById);
            return new ResponseEntity<>(cust, HttpStatus.FOUND);
        } else {
            cust = customerRepo.save(customer);
            return new ResponseEntity<>(cust, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<String> deleteCustomer(String custId) {
        Customer custById = customerRepo.getReferenceById(custId);
        if (custById != null) {
            customerRepo.delete(custById);
            return new ResponseEntity<>("record deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("record not deleted", HttpStatus.NOT_FOUND);
        }
    }
}
