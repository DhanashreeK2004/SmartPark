package com.apartment.parking.service;

import com.apartment.parking.entity.ParkingSlot;
import java.util.List;

public interface ParkingSlotService {

    ParkingSlot addSlot(ParkingSlot slot);

    List<ParkingSlot> getAllSlots();

    ParkingSlot getSlotById(Long id);

    void deleteSlot(Long id);

    ParkingSlot allocateSlot(Long userId);

    void releaseSlot(Long slotId);

    List<ParkingSlot> getAvailableSlots();

    long getTotalSlots();
    long getAvailableSlotsCount();

}