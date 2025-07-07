package com.hotelapp.hotel.service;

import com.hotelapp.hotel.dto.HotelRequestDTO;
import com.hotelapp.hotel.dto.HotelResponseDTO;
import com.hotelapp.hotel.mapper.HotelMapper;
import com.hotelapp.hotel.model.Hotel;
import com.hotelapp.hotel.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HotelServiceTests {

    private HotelRepository hotelRepository;
    private HotelMapper hotelMapper;
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelRepository = mock(HotelRepository.class);
        hotelMapper = mock(HotelMapper.class);
        hotelService = new HotelService(hotelRepository, hotelMapper);
    }

    @Test
    void testGetAllHotels_ReturnsHotelResponseDTOList() {
        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setName("Hotel A");
        hotel1.setAddress("Address A");
        hotel1.setStarRating(4);

        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setName("Hotel B");
        hotel2.setAddress("Address B");
        hotel2.setStarRating(5);

        when(hotelRepository.findAll()).thenReturn(List.of(hotel1, hotel2));

        when(hotelMapper.toResponseDTO(hotel1)).thenReturn(
                new HotelResponseDTO(1L, "Hotel A", "Address A", 4, null, null)
        );
        when(hotelMapper.toResponseDTO(hotel2)).thenReturn(
                new HotelResponseDTO(2L, "Hotel B", "Address B", 5, null, null)
        );

        List<HotelResponseDTO> result = hotelService.getAllHotels();

        assertEquals(2, result.size());
        assertEquals("Hotel A", result.get(0).getName());
        assertEquals("Hotel B", result.get(1).getName());

        verify(hotelRepository, times(1)).findAll();
        verify(hotelMapper, times(2)).toResponseDTO(any(Hotel.class));
    }

    @Test
    void testCreateHotel_ReturnsHotelResponseDTO() {
        HotelRequestDTO requestDTO = new HotelRequestDTO();
        requestDTO.setName("New Hotel");
        requestDTO.setAddress("New Address");
        requestDTO.setStarRating(3);

        Hotel hotelEntity = new Hotel();
        hotelEntity.setName("New Hotel");
        hotelEntity.setAddress("New Address");
        hotelEntity.setStarRating(3);

        Hotel savedEntity = new Hotel();
        savedEntity.setId(1L);
        savedEntity.setName("New Hotel");
        savedEntity.setAddress("New Address");
        savedEntity.setStarRating(3);

        HotelResponseDTO expectedResponse = new HotelResponseDTO(
                1L, "New Hotel", "New Address", 3, null, null
        );

        when(hotelMapper.toEntity(requestDTO)).thenReturn(hotelEntity);
        when(hotelRepository.save(hotelEntity)).thenReturn(savedEntity);
        when(hotelMapper.toResponseDTO(savedEntity)).thenReturn(expectedResponse);

        HotelResponseDTO actual = hotelService.createHotel(requestDTO);

        assertNotNull(actual);
        assertEquals("New Hotel", actual.getName());
        assertEquals(1L, actual.getId());

        verify(hotelMapper).toEntity(requestDTO);
        verify(hotelRepository).save(hotelEntity);
        verify(hotelMapper).toResponseDTO(savedEntity);
    }
}
