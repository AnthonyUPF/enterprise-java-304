package com.example.Intro.repositories;



import com.example.Intro.models.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightRepositoryTest {
    @Autowired
    FlightRepository flightRepository;
    Flight flight1;
    Flight flight2;
    Flight flight3;
    Flight flight4;
    Flight flight5;

    @BeforeEach
    void setUp() {
        flight1= new Flight("DL143", 135l, "Boeing 747");
        flight2 = new Flight("DL122", 4370l, "Airbus A330");
        flight3 = new Flight("DL53", 2078l, "Boeing 777");
        flight4 = new Flight("DL222", 1765l, "Boeing 777");
        flight5 = new Flight("DL37", 531l, "Boeing 747");

        flightRepository.saveAll(List.of(flight1, flight2, flight3, flight4, flight5));
    }


    @AfterEach
    void deleteFlightRepository() {
        flightRepository.deleteAll();
    }



    //
    @Test
    void shouldReturnFlight_UsingValidId() {
        Optional<Flight> newFlightOptional = flightRepository.findById(flight3.getNumber());
        if (newFlightOptional.isPresent()) {
            assertEquals(flight3.getNumber(), newFlightOptional.get().getNumber());
            assertTrue(flight3.getMileage().equals(newFlightOptional.get().getMileage()));
            assertFalse(!flight3.getAircraft().equals(newFlightOptional.get().getAircraft()));
        }
    }

    @Test
    void shouldReturnListFlightByNumber() {
        List<Flight> newFlightRepository = flightRepository.findByNumberContaining(flight3.getNumber());
        if (!newFlightRepository.isEmpty()) {
            assertTrue(newFlightRepository.size() > 0);
        }
    }

    @Test
    void shouldReturnListFlightByMilesGreaterThanSomeNumber() {
        List<Flight> newFlightRepository = flightRepository.findByMileageGreaterThan(500l);
        if (!newFlightRepository.isEmpty()) {
            assertTrue(newFlightRepository.size() > 3);
            assertEquals(4,newFlightRepository.size());
        }
    }

    @Test
    void shouldReturnListObjectUsingFindMaxMileageByAircraft() {
        List<Object[]> newFlightRepository = flightRepository.findMaxMileageByAircraft();
        if (!newFlightRepository.isEmpty()) {
            assertEquals(3, newFlightRepository.size());
        }
    }

    @Test
    void shouldReturnListObjectUsingFindMaxMileageWithAircraft() {
        List<Object[]> newFlightRepository = flightRepository.findMaxMileageWithAircraft("Boeing 747");
        if (!newFlightRepository.isEmpty()) {
            assertEquals(2, newFlightRepository.size());
        }
    }



}
