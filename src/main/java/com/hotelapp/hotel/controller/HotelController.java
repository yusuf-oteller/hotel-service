package com.hotelapp.hotel.controller;

import com.hotelapp.hotel.dto.HotelRequestDTO;
import com.hotelapp.hotel.model.Hotel;
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
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hotel createHotel(@Valid @RequestBody HotelRequestDTO hotelDto) {
        return hotelService.createHotel(hotelDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelRequestDTO hotelDto) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
