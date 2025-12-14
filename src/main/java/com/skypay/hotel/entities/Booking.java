package com.skypay.hotel.entities;

import java.util.Date;

public class Booking {
    private static int bookingCounter = 0;

    private int bookingId;
    private int userId;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;

    // Store room and user data at time of booking
    private RoomType roomTypeAtBooking;
    private int roomPriceAtBooking;
    private int userBalanceBeforeBooking;
    private int totalCost;

    public Booking(int userId, int roomNumber, Date checkIn, Date checkOut,
                   RoomType roomType, int roomPrice, int userBalance, int totalCost) {
        this.bookingId = ++bookingCounter;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomTypeAtBooking = roomType;
        this.roomPriceAtBooking = roomPrice;
        this.userBalanceBeforeBooking = userBalance;
        this.totalCost = totalCost;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Booking #" + bookingId +
                " [User: " + userId +
                ", Room: " + roomNumber +
                ", Type: " + roomTypeAtBooking +
                ", Price/night: " + roomPriceAtBooking +
                ", Check-in: " + checkIn +
                ", Check-out: " + checkOut +
                ", Total Cost: " + totalCost +
                ", User Balance Before: " + userBalanceBeforeBooking + "]";
    }
}
