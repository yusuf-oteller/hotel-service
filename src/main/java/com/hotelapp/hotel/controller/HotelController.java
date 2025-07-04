package com.hotelapp.hotel.controller;

import com.hotelapp.hotel.dto.HotelRequestDTO;
import com.hotelapp.hotel.dto.HotelResponseDTO;
import com.hotelapp.hotel.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelResponseDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@RequestBody @Valid HotelRequestDTO hotelRequestDTO) {
        return ResponseEntity.ok(hotelService.createHotel(hotelRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable Long id,
                                                        @RequestBody @Valid HotelRequestDTO hotelRequestDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelRequestDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
