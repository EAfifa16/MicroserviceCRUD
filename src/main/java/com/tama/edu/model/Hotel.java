package com.tama.edu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    String hotelId;
    String custId;
    String hotelName;
    String hotelAddress;
    String hotelReview;
}
