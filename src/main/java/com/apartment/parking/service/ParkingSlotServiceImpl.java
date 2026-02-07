package com.apartment.parking.service;

import com.apartment.parking.entity.ParkingSlot;
import com.apartment.parking.entity.User;
import com.apartment.parking.repository.UserRepository;
import com.apartment.parking.repository.ParkingSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository repository;
    private final UserRepository userRepository;
    public ParkingSlotServiceImpl(ParkingSlotRepository repository,UserRepository userRepository) {
        this.repository = repository;
        this.userRepository=userRepository;
    }

    @Override
    public ParkingSlot addSlot(ParkingSlot slot) {
        return repository.save(slot);
    }

    @Override
    public List<ParkingSlot> getAllSlots(){
        return repository.findAll();
    }

    @Override
    public ParkingSlot getSlotById(Long id) {
        Optional<ParkingSlot> slot = repository.findById(id);
        return slot.orElse(null);
    }

    @Override
    public void deleteSlot(Long id){
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public synchronized ParkingSlot allocateSlot(Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getParkingSlot()!=null){
            throw new RuntimeException("User already has a parking slot");
        }
        ParkingSlot slot=repository.findFirstByOccupiedFalseOrderBySlotNumberAsc().orElseThrow(()->new RuntimeException("No free parking slots available"));

        slot.setOccupied(true);
        slot=repository.save(slot);
        user.setParkingSlot(slot);
        userRepository.save(user);
        return slot;
    }
    @Override
    @Transactional
    public synchronized void releaseSlot(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ParkingSlot slot = user.getParkingSlot();

        if (slot == null) {
            throw new RuntimeException("User has no allocated slot");
        }

        slot.setOccupied(false);
        repository.save(slot);

        user.setParkingSlot(null);
        userRepository.save(user);
    }

    @Override
    public List<ParkingSlot>
    getAvailableSlots(){
        return repository.findByOccupiedFalse();
    }

    @Override
    public long getTotalSlots() {
        return repository.count();
    }

    @Override
    public long getAvailableSlotsCount() {
        return repository.countByOccupiedFalse();
    }
}