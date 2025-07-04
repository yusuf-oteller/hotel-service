package com.hotelapp.hotel.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDTO {
    private Long id;
    private String name;
    private String address;
    private int starRating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
