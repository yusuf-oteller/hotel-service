package com.hotelapp.hotel.service;

import com.hotelapp.hotel.dto.RoomRequestDTO;
import com.hotelapp.hotel.dto.RoomResponseDTO;
import com.hotelapp.hotel.mapper.RoomMapper;
import com.hotelapp.hotel.model.Room;
import com.hotelapp.hotel.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomServiceTests {

    private RoomRepository roomRepository;
    private RoomMapper roomMapper;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomRepository = mock(RoomRepository.class);
        roomMapper = mock(RoomMapper.class);
        roomService = new RoomService(roomRepository, roomMapper);
    }

    @Test
    void testGetRoomsByHotelId_ReturnsRoomResponseDTOList() {
        Room room1 = new Room();
        room1.setId(1L);
        room1.setHotelId(101L);
        room1.setRoomNumber("101A");
        room1.setCapacity(2);
        room1.setPricePerNight(BigDecimal.valueOf(100));

        Room room2 = new Room();
        room2.setId(2L);
        room2.setHotelId(101L);
        room2.setRoomNumber("102B");
        room2.setCapacity(3);
        room2.setPricePerNight(BigDecimal.valueOf(150));

        when(roomRepository.findByHotelId(101L)).thenReturn(List.of(room1, room2));

        when(roomMapper.toResponseDTO(room1)).thenReturn(
                new RoomResponseDTO(1L, 101L, "101A", 2, BigDecimal.valueOf(100), null, null)
        );
        when(roomMapper.toResponseDTO(room2)).thenReturn(
                new RoomResponseDTO(2L, 101L, "102B", 3, BigDecimal.valueOf(150), null, null)
        );

        List<RoomResponseDTO> result = roomService.getRoomsByHotelId(101L);

        assertEquals(2, result.size());
        assertEquals("101A", result.get(0).getRoomNumber());
        assertEquals("102B", result.get(1).getRoomNumber());

        verify(roomRepository).findByHotelId(101L);
        verify(roomMapper, times(2)).toResponseDTO(any(Room.class));
    }

    @Test
    void testCreateRoom_ReturnsRoomResponseDTO() {
        RoomRequestDTO requestDTO = new RoomRequestDTO();
        requestDTO.setHotelId(101L);
        requestDTO.setRoomNumber("201C");
        requestDTO.setCapacity(4);
        requestDTO.setPricePerNight(BigDecimal.valueOf(180));

        Room entity = new Room();
        entity.setHotelId(101L);
        entity.setRoomNumber("201C");
        entity.setCapacity(4);
        entity.setPricePerNight(BigDecimal.valueOf(180));

        Room saved = new Room();
        saved.setId(3L);
        saved.setHotelId(101L);
        saved.setRoomNumber("201C");
        saved.setCapacity(4);
        saved.setPricePerNight(BigDecimal.valueOf(180));

        RoomResponseDTO responseDTO = new RoomResponseDTO(
                3L, 101L, "201C", 4, BigDecimal.valueOf(180), null, null
        );

        when(roomMapper.toEntity(requestDTO)).thenReturn(entity);
        when(roomRepository.save(entity)).thenReturn(saved);
        when(roomMapper.toResponseDTO(saved)).thenReturn(responseDTO);

        RoomResponseDTO result = roomService.createRoom(requestDTO);

        assertNotNull(result);
        assertEquals("201C", result.getRoomNumber());
        assertEquals(3L, result.getId());

        verify(roomMapper).toEntity(requestDTO);
        verify(roomRepository).save(entity);
        verify(roomMapper).toResponseDTO(saved);
    }
}
