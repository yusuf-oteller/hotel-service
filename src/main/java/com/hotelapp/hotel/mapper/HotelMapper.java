package com.hotelapp.hotel.mapper;

import com.hotelapp.hotel.dto.HotelRequestDTO;
import com.hotelapp.hotel.dto.HotelResponseDTO;
import com.hotelapp.hotel.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public Hotel toEntity(HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setStarRating(dto.getStarRating());
        return hotel;
    }

    public HotelResponseDTO toResponseDTO(Hotel hotel) {
        return new HotelResponseDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getStarRating(),
                hotel.getCreatedAt(),
                hotel.getUpdatedAt()
        );
    }

    public void updateHotelFromDto(Hotel hotel, HotelRequestDTO dto) {
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setStarRating(dto.getStarRating());
    }
}
