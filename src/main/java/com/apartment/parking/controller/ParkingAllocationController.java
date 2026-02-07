package com.apartment.parking.controller;

import com.apartment.parking.entity.ParkingSlot;
import com.apartment.parking.service.ParkingSlotService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/parking")
public class ParkingAllocationController {

    private final ParkingSlotService parkingSlotService;

    public ParkingAllocationController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    // 🚗 Allocate nearest free parking slot
    @PostMapping("/allocate/{userId}")
    public ParkingSlot allocateSlot(@PathVariable Long userId) {
        return parkingSlotService.allocateSlot(userId);
    }

    // 🔁 Release parking slot by user
    @PostMapping("/release/{userId}")
    public String releaseSlot(@PathVariable Long userId) {
        parkingSlotService.releaseSlot(userId);
        return "Parking slot released successfully";
    }
}