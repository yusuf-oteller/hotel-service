package com.hotelapp.hotel.integration;

import com.hotelapp.hotel.model.Hotel;
import com.hotelapp.hotel.repository.HotelRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HotelServiceIntegrationTest {

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

    @BeforeEach
    void setUp() {
        hotelRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldSaveHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Test Hotel");
        hotel.setAddress("123 Street");
        hotel.setStarRating(4);

        Hotel saved = hotelRepository.save(hotel);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Test Hotel");
    }

    @Test
    @Order(2)
    void shouldFetchAllHotels() {
        hotelRepository.save(new Hotel(null, "Hotel A", "Address A", 3, null, null));
        hotelRepository.save(new Hotel(null, "Hotel B", "Address B", 5, null, null));

        List<Hotel> hotels = hotelRepository.findAll();

        assertThat(hotels).hasSize(2);
    }

    @Test
    @Order(3)
    void shouldDeleteHotel() {
        Hotel hotel = hotelRepository.save(new Hotel(null, "ToDelete", "Some Street", 2, null, null));

        hotelRepository.deleteById(hotel.getId());

        assertThat(hotelRepository.findById(hotel.getId())).isEmpty();
    }
}
