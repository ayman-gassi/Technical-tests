# 1.Is putting all functions in the same service recommended?
No, it's not recommended. It works for small projects but causes problems:

- One giant class doing everything is hard to maintain
- Testing becomes difficult (can't test booking without user/room code)
- Team conflicts when multiple devs edit the same file
- Violates Single Responsibility Principle

The best way is to make each service has one job. If something breaks, you know exactly where to look. Easy to add new features without touching existing code.

# 2: What is another way to handle setRoom()? What's your recommendation?

In this approch we store room details (type, price) inside Booking at booking time. Later changes to room don't affect past bookings.

Alternative: Make rooms immutable after first booking. Can't modify a room if it has bookings, must create new version.

For my recommendation the current approach is better because :
- Hotels change prices with seasons or holidays ...
- Immutable rooms can't update Room 101 if someone booked it 2 years ago
- Would need Room 101_v2, Room 101_v3... Its confusing
- In the current design, hotel has flexibility, customer's receipt stays accurate

So Storing snapshot in Booking protects transaction history while allowing
room updates.