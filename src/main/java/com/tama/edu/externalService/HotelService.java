package com.tama.edu.externalService;

import com.tama.edu.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "HOTEL-MICROSERVICE")
public interface HotelService {

    @GetMapping("hotel/getHotelById/{custId}")
    List<Hotel> getHotelById(@PathVariable("custId") String custId);

}
