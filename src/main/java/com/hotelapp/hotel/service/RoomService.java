package com.hotelapp.hotel.service;

import com.hotelapp.hotel.dto.RoomRequestDTO;
import com.hotelapp.hotel.dto.RoomResponseDTO;
import com.hotelapp.hotel.mapper.RoomMapper;
import com.hotelapp.hotel.model.Room;
import com.hotelapp.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomResponseDTO> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(roomMapper::toResponseDTO)
                .toList();
    }

    public RoomResponseDTO createRoom(RoomRequestDTO dto) {
        Room room = roomMapper.toEntity(dto);
        Room saved = roomRepository.save(room);
        return roomMapper.toResponseDTO(saved);
    }

    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO dto) {
        return roomRepository.findById(id)
                .map(room -> {
                    roomMapper.updateRoomFromDto(room, dto);
                    Room updated = roomRepository.save(room);
                    return roomMapper.toResponseDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
