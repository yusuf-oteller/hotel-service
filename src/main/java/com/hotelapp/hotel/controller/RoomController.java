package com.hotelapp.hotel.controller;

import com.hotelapp.hotel.dto.RoomRequestDTO;
import com.hotelapp.hotel.dto.RoomResponseDTO;
import com.hotelapp.hotel.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getRoomsByHotelId(hotelId));
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody @Valid RoomRequestDTO roomRequestDTO) {
        return ResponseEntity.ok(roomService.createRoom(roomRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Long id,
                                                      @RequestBody @Valid RoomRequestDTO roomRequestDTO) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
