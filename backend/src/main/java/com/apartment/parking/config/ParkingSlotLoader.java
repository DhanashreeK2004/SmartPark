package com.apartment.parking.config;

import com.apartment.parking.entity.ParkingSlot;
import com.apartment.parking.repository.ParkingSlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingSlotLoader {

    @Bean
    CommandLineRunner loadParkingSlots(ParkingSlotRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                for (int i = 1; i <= 100; i++) {
                    repository.save(new ParkingSlot(i, false));
                }
            }
        };
    }
}