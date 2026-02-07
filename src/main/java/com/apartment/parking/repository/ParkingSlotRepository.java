package com.apartment.parking.repository;

import com.apartment.parking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    Optional<ParkingSlot>
    findFirstByOccupiedFalseOrderBySlotNumberAsc();
    List<ParkingSlot>
    findByOccupiedFalse();
    long count();
    long countByOccupiedFalse();
}