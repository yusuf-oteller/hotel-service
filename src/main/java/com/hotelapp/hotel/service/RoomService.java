package com.hotelapp.hotel.service;

import com.hotelapp.hotel.dto.RoomRequestDTO;
import com.hotelapp.hotel.model.Room;
import com.hotelapp.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(RoomRequestDTO dto) {
        Room room = new Room();
        room.setHotelId(dto.getHotelId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setCapacity(dto.getCapacity());
        room.setPricePerNight(dto.getPricePerNight());
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, RoomRequestDTO dto) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomNumber(dto.getRoomNumber());
            room.setCapacity(dto.getCapacity());
            room.setPricePerNight(dto.getPricePerNight());
            return roomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }


    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
