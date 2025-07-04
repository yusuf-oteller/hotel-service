package com.hotelapp.hotel.service;

import com.hotelapp.hotel.dto.HotelRequestDTO;
import com.hotelapp.hotel.model.Hotel;
import com.hotelapp.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel createHotel(HotelRequestDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setStarRating(dto.getStarRating());
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, HotelRequestDTO dto) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setName(dto.getName());
            hotel.setAddress(dto.getAddress());
            hotel.setStarRating(dto.getStarRating());
            return hotelRepository.save(hotel);
        }).orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
