package com.example.Intro.repositories;

import com.example.Intro.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByNumberContaining(String number);

    List<Flight> findByMileageGreaterThan(Long mileage);

    @Query("SELECT aircraft, MAX(mileage) FROM Flight GROUP BY aircraft")
    List<Object[]> findMaxMileageByAircraft();

    @Query("SELECT aircraft, MAX(mileage) FROM Flight WHERE aircraft >=?1 GROUP BY aircraft")
    List<Object[]> findMaxMileageWithAircraft(String aircraft);

}
