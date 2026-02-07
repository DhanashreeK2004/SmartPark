package com.apartment.parking.service;

import com.apartment.parking.entity.User;
import com.apartment.parking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.apartment.parking.dto.UserParkingResponse;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔐 SIGN UP
    @Override
    public User register(User user) {

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // 🔓 LOGIN (email or phone)
    @Override
    public User login(String identifier, String password) {

        User user = userRepository.findByEmail(identifier)
                .orElseGet(() -> userRepository.findByPhoneNumber(identifier)
                        .orElseThrow(() ->
                                new RuntimeException("User not found")));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
    @Override
    public UserParkingResponse getUserParkingDetails(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getParkingSlot() == null) {
            throw new RuntimeException("User has no allocated parking slot");
        }

        return new UserParkingResponse(
                user.getId(),
                user.getName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getParkingSlot().getSlotNumber(),
                user.getParkingSlot().isOccupied()

        );
    }
}