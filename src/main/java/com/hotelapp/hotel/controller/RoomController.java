package com.hotelapp.hotel.controller;

import com.hotelapp.hotel.dto.RoomRequestDTO;
import com.hotelapp.hotel.model.Room;
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
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotelId(hotelId);
    }

    @PostMapping
    public Room createRoom(@Valid @RequestBody RoomRequestDTO roomDto) {
        return roomService.createRoom(roomDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomRequestDTO roomDto) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
