package com.hotelapp.hotel.integration;

import com.hotelapp.hotel.model.Hotel;
import com.hotelapp.hotel.model.Room;
import com.hotelapp.hotel.repository.HotelRepository;
import com.hotelapp.hotel.repository.RoomRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        roomRepository.deleteAll();
        hotelRepository.deleteAll();

        hotel = new Hotel();
        hotel.setName("Test Hotel");
        hotel.setAddress("Hotel Street");
        hotel.setStarRating(5);
        hotel = hotelRepository.save(hotel);
    }

    @Test
    @Order(1)
    void shouldSaveRoom() {
        Room room = new Room();
        room.setHotelId(hotel.getId());
        room.setRoomNumber("101");
        room.setCapacity(2);
        room.setPricePerNight(BigDecimal.valueOf(120.00));

        Room saved = roomRepository.save(room);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getHotelId()).isEqualTo(hotel.getId());
        assertThat(saved.getRoomNumber()).isEqualTo("101");
    }

    @Test
    @Order(2)
    void shouldFetchRoomsByHotelId() {
        roomRepository.save(new Room(null, hotel.getId(), "102", 3, BigDecimal.valueOf(150.00), null, null));
        roomRepository.save(new Room(null, hotel.getId(), "103", 4, BigDecimal.valueOf(180.00), null, null));

        List<Room> rooms = roomRepository.findByHotelId(hotel.getId());

        assertThat(rooms).hasSize(2);
    }

    @Test
    @Order(3)
    void shouldDeleteRoom() {
        Room room = roomRepository.save(new Room(null, hotel.getId(), "104", 1, BigDecimal.valueOf(90.00), null, null));

        roomRepository.deleteById(room.getId());

        assertThat(roomRepository.findById(room.getId())).isEmpty();
    }
}
