package com.hotelapp.hotel.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomRequestDTO {
    @NotNull(message = "Hotel ID is required")
    private Long hotelId;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    @NotNull(message = "Price per night is required")
    private BigDecimal pricePerNight;
}
