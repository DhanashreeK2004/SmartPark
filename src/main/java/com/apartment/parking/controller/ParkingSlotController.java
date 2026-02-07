package com.apartment.parking.controller;

import com.apartment.parking.entity.ParkingSlot;
import com.apartment.parking.service.ParkingSlotService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/slots")
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    // Add new parking slot
    @PostMapping
    public ParkingSlot addSlot(@RequestBody ParkingSlot slot) {
        return parkingSlotService.addSlot(slot);
    }

    // Get all parking slots
    @GetMapping
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotService.getAllSlots();
    }

    // Get slot by id
    @GetMapping("/{id}")
    public ParkingSlot getSlotById(@PathVariable Long id) {
        return parkingSlotService.getSlotById(id);
    }

    // Delete slot
    @DeleteMapping("/{id}")
    public String deleteSlot(@PathVariable Long id) {
        parkingSlotService.deleteSlot(id);
        return "Parking slot deleted successfully";
    }

    @GetMapping("/available")
    public List<ParkingSlot>
    getAvailableSlots(){
        return parkingSlotService.getAvailableSlots();
    }

    @GetMapping("/count")
    public Map<String, Long> getSlotCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("total", parkingSlotService.getTotalSlots());
        counts.put("available", parkingSlotService.getAvailableSlotsCount());
        return counts;
    }
}