package com.hotelapp.hotel.dto;

import jakarta.validation.constraints.*;

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
    @Max(value = 5, message = "Star rating must not exceed 5")
    private int starRating;
}
