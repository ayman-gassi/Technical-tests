package com.skypay.hotel.services;

import com.skypay.hotel.entities.Booking;
import com.skypay.hotel.entities.Room;
import com.skypay.hotel.entities.RoomType;
import com.skypay.hotel.entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Service {
    private ArrayList<Room> rooms;
    private ArrayList<User> users;
    private ArrayList<Booking> bookings;

    public Service() {
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        if (roomPricePerNight < 0) {
            throw new IllegalArgumentException("Room price cannot be negative");
        }

        Room existingRoom = findRoom(roomNumber);

        if (existingRoom != null) {
            existingRoom.setRoomType(roomType);
            existingRoom.setPricePerNight(roomPricePerNight);
            System.out.println("Updated: " + existingRoom);
        } else {
            Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
            rooms.add(newRoom);
            System.out.println("Created: " + newRoom);
        }
    }

    public void setUser(int userId, int balance) {
        User existingUser = findUser(userId);

        if (existingUser != null) {
            existingUser.setBalance(balance);
            System.out.println("Updated: " + existingUser);
        } else {
            User newUser = new User(userId, balance);
            users.add(newUser);
            System.out.println("Created: " + newUser);
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        try {
            // Validate dates
            if (checkIn.after(checkOut) || checkIn.equals(checkOut)) {
                throw new IllegalArgumentException("Invalid dates: check-in must be before check-out");
            }

            // Find user and room
            User user = findUser(userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + userId);
            }

            Room room = findRoom(roomNumber);
            if (room == null) {
                throw new IllegalArgumentException("Room not found: " + roomNumber);
            }

            // Calculate number of nights
            int nights = calculateNights(checkIn, checkOut);
            int totalCost = nights * room.getPricePerNight();

            // Check if user has enough balance
            if (user.getBalance() < totalCost) {
                throw new IllegalArgumentException("Insufficient balance. Required: " + totalCost + ", Available: " + user.getBalance());
            }

            // Check if room is available
            if (!isRoomAvailable(roomNumber, checkIn, checkOut)) {
                throw new IllegalArgumentException("Room not available for the specified period");
            }

            // Store user balance before booking
            int balanceBeforeBooking = user.getBalance();

            // Create booking
            Booking booking = new Booking(userId, roomNumber, checkIn, checkOut,
                    room.getRoomType(), room.getPricePerNight(),
                    balanceBeforeBooking, totalCost);
            bookings.add(booking);

            // Deduct balance
            user.deductBalance(totalCost);

            System.out.println("SUCCESS: Booking created - " + booking);
            System.out.println("User " + userId + " new balance: " + user.getBalance());

        } catch (Exception e) {
            System.out.println("FAILED: Booking failed - " + e.getMessage());
        }
    }

    public void printAll() {
        System.out.println("\n========== ALL ROOMS (Latest to Oldest) ==========");
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            for (int i = rooms.size() - 1; i >= 0; i--) {
                System.out.println(rooms.get(i));
            }
        }

        System.out.println("\n--> ALL BOOKINGS ");
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (int i = bookings.size() - 1; i >= 0; i--) {
                System.out.println(bookings.get(i));
            }
        }
    }

    public void printAllUsers() {
        System.out.println("\n---> ALL USERS ");
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (int i = users.size() - 1; i >= 0; i--) {
                System.out.println(users.get(i));
            }
        }
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private User findUser(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == roomNumber) {
                // Check if dates overlap
                if (datesOverlap(checkIn, checkOut, booking.getCheckIn(), booking.getCheckOut())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean datesOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !start1.after(end2) && !end1.before(start2);
    }

    private int calculateNights(Date checkIn, Date checkOut) {
        long diffInMillis = checkOut.getTime() - checkIn.getTime();
        return (int) (diffInMillis / (1000 * 60 * 60 * 24));
    }
}

