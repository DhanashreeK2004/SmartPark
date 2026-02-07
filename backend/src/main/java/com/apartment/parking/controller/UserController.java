package com.apartment.parking.controller;
import com.apartment.parking.dto.UserParkingResponse;
import org.springframework.web.bind.annotation.PathVariable;
import com.apartment.parking.entity.User;
import com.apartment.parking.service.UserService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🟢 SIGN UP
    @PostMapping("/signup")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // 🔵 LOGIN (email OR phone)
    @PostMapping("/login")
    public User login(@RequestParam String identifier,
                      @RequestParam String password) {
        return userService.login(identifier, password);
    }
    @GetMapping("/{userId}/parking")
    public UserParkingResponse getUserParkingDetails(@PathVariable Long userId) {
        return userService.getUserParkingDetails(userId);
    }
}