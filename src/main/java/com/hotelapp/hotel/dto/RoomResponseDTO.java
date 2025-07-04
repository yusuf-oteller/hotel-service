package com.hotelapp.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {
    private Long id;
    private Long hotelId;
    private String roomNumber;
    private int capacity;
    private BigDecimal pricePerNight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
