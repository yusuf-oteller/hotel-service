package com.hotelapp.hotel.mapper;

import com.hotelapp.hotel.dto.RoomRequestDTO;
import com.hotelapp.hotel.dto.RoomResponseDTO;
import com.hotelapp.hotel.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room toEntity(RoomRequestDTO dto) {
        Room room = new Room();
        room.setHotelId(dto.getHotelId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setCapacity(dto.getCapacity());
        room.setPricePerNight(dto.getPricePerNight());
        return room;
    }

    public RoomResponseDTO toResponseDTO(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getHotelId(),
                room.getRoomNumber(),
                room.getCapacity(),
                room.getPricePerNight(),
                room.getCreatedAt(),
                room.getUpdatedAt()
        );
    }

    public void updateRoomFromDto(Room room, RoomRequestDTO dto) {
        room.setRoomNumber(dto.getRoomNumber());
        room.setCapacity(dto.getCapacity());
        room.setPricePerNight(dto.getPricePerNight());
    }
}
