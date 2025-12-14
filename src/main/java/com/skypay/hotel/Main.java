package com.skypay.hotel;

import com.skypay.hotel.entities.RoomType;
import com.skypay.hotel.services.Service;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Skypay Hotel Reservation Test =====\n");

        Service service = new Service();

        // Create 3 rooms
        System.out.println("--- Creating Rooms ---");
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);
        // Create 2 users
        System.out.println("\n--- Creating Users ---");
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        // Test 1
        Date date1 = createDate(2026, 6, 30);
        Date date2 = createDate(2026, 7, 7);
        System.out.println("\n1. User 1 booking Room 2 (30/06/2026 to 07/07/2026):");
        service.bookRoom(1, 2, date1, date2);

        // Test 2
        System.out.println("\n2. User 1 booking Room 2 (07/07/2026 to 30/06/2026 - invalid):");
        service.bookRoom(1, 2, date2, date1);

        // Test 3
        Date date3 = createDate(2026, 7, 8);
        System.out.println("\n3. User 1 booking Room 1 (07/07/2026 to 08/07/2026):");
        service.bookRoom(1, 1, date2, date3);

        // Test4
        Date date4 = createDate(2026, 7, 9);
        System.out.println("\n4. User 2 booking Room 1 (07/07/2026 to 09/07/2026):");
        service.bookRoom(2, 1, date2, date4);

        // Test 5
        System.out.println("\n5. User 2 booking Room 3 (07/07/2026 to 08/07/2026):");
        service.bookRoom(2, 3, date2, date3);

        // Update Room 1
        System.out.println("\n--- Updating Room 1 ---");
        service.setRoom(1, RoomType.SUITE, 10000);

        // Print all data
        service.printAll();
        service.printAllUsers();
    }

    private static Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // Month is 0-based
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
