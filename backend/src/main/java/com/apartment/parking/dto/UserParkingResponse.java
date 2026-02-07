package com.apartment.parking.dto;

public class UserParkingResponse {

    private Long userId;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer slotNumber;
    private boolean occupied;


    public UserParkingResponse(Long userId, String name,String phoneNumber,String email,
                               Integer slotNumber, boolean occupied) {
        this.userId = userId;
        this.name = name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.slotNumber = slotNumber;
        this.occupied = occupied;

    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }


    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmail(){
        return email;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

}