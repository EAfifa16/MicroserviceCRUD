package com.tama.edu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer_microservice")
public class Customer {

    @Id
    @Column(name = "customer_id")
    String custId;

    @Column(name = "customer_name")
    String custName;

    @Column(name = "customer_address")
    String custAddress;

    @Transient // as we are not saving this to DB
    List<Hotel> hotel;

}
