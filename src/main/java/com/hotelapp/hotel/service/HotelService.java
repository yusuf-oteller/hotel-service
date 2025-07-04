package com.hotelapp.hotel.service;

import com.hotelapp.hotel.dto.HotelRequestDTO;
import com.hotelapp.hotel.dto.HotelResponseDTO;
import com.hotelapp.hotel.mapper.HotelMapper;
import com.hotelapp.hotel.model.Hotel;
import com.hotelapp.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public List<HotelResponseDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toResponseDTO)
                .toList();
    }

    public Optional<HotelResponseDTO> getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toResponseDTO);
    }

    public HotelResponseDTO createHotel(HotelRequestDTO requestDTO) {
        Hotel hotel = hotelMapper.toEntity(requestDTO);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(saved);
    }

    public HotelResponseDTO updateHotel(Long id, HotelRequestDTO requestDTO) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    hotelMapper.updateHotelFromDto(hotel, requestDTO);
                    Hotel updated = hotelRepository.save(hotel);
                    return hotelMapper.toResponseDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
