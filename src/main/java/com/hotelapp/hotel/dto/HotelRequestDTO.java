package com.hotelapp.hotel.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequestDTO {
    @NotBlank(message = "Hotel name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 1, message = "Star rating must be at least 1")
    private int starRating;
}
