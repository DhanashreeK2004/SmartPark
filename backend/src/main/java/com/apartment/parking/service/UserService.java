package com.apartment.parking.service;
import com.apartment.parking.dto.UserParkingResponse;
import com.apartment.parking.entity.User;
import com.apartment.parking.dto.UserParkingResponse;
public interface UserService {
    User register(User user);
    User login(String identifier,String password);
    UserParkingResponse getUserParkingDetails(Long userId);
}
