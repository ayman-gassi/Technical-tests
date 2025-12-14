import com.skypay.hotel.entities.RoomType;
import com.skypay.hotel.services.Service;

import java.util.Calendar;
import java.util.Date;

public class HotelTest {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    HOTEL RESERVATION TEST CASE");
        System.out.println("========================================\n");

        Service service = new Service();

        // Step 1: Create 3 rooms
        System.out.println("STEP 1: Creating 3 Rooms");
        System.out.println("------------------------");
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        // Step 2: Create 2 users
        System.out.println("\nSTEP 2: Creating 2 Users");
        System.out.println("------------------------");
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        // Step 3: User 1 books Room 2 (30/06/2026 to 07/07/2026) - 7 nights = 14000
        System.out.println("\nSTEP 3: User 1 tries booking Room 2 (30/06/2026 to 07/07/2026)");
        System.out.println("Expected: FAIL (Insufficient balance: needs 14000, has 5000)");
        System.out.println("------------------------");
        service.bookRoom(1, 2, createDate(2026, 6, 30), createDate(2026, 7, 7));

        // Step 4: User 1 books Room 2 (07/07/2026 to 30/06/2026) - Invalid dates
        System.out.println("\nSTEP 4: User 1 tries booking Room 2 (07/07/2026 to 30/06/2026)");
        System.out.println("Expected: FAIL (Invalid dates - checkout before checkin)");
        System.out.println("------------------------");
        service.bookRoom(1, 2, createDate(2026, 7, 7), createDate(2026, 6, 30));

        // Step 5: User 1 books Room 1 (07/07/2026 to 08/07/2026) - 1 night = 1000
        System.out.println("\nSTEP 5: User 1 tries booking Room 1 (07/07/2026 to 08/07/2026)");
        System.out.println("Expected: SUCCESS (1 night, 1000 cost, user has 5000)");
        System.out.println("------------------------");
        service.bookRoom(1, 1, createDate(2026, 7, 7), createDate(2026, 7, 8));

        // Step 6: User 2 books Room 1 (07/07/2026 to 09/07/2026) - Overlap
        System.out.println("\nSTEP 6: User 2 tries booking Room 1 (07/07/2026 to 09/07/2026)");
        System.out.println("Expected: FAIL (Room already booked by User 1)");
        System.out.println("------------------------");
        service.bookRoom(2, 1, createDate(2026, 7, 7), createDate(2026, 7, 9));

        // Step 7: User 2 books Room 3 (07/07/2026 to 08/07/2026) - 1 night = 3000
        System.out.println("\nSTEP 7: User 2 tries booking Room 3 (07/07/2026 to 08/07/2026)");
        System.out.println("Expected: SUCCESS (1 night, 3000 cost, user has 10000)");
        System.out.println("------------------------");
        service.bookRoom(2, 3, createDate(2026, 7, 7), createDate(2026, 7, 8));

        // Step 8: Update Room 1
        System.out.println("\nSTEP 8: Updating Room 1 (Type: SUITE, Price: 10000)");
        System.out.println("Expected: Room updated, but previous booking unchanged");
        System.out.println("------------------------");
        service.setRoom(1, RoomType.SUITE, 10000);

        // Final Results
        System.out.println("\n\n========================================");
        System.out.println("         FINAL RESULTS");
        System.out.println("========================================");

        service.printAll();
        service.printAllUsers();

        System.out.println("\n========================================");
        System.out.println("         TEST COMPLETE");
        System.out.println("========================================");
    }

    private static Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}

