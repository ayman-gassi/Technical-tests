# Technical Tests - Java Projects

This repository contains my solutions to the  technical assessment tests.

## Project Overview

This repository includes two complete Java projects demonstrating core programming concepts, clean architecture, and best practices.

### Test 1: Banking Service System
A simple yet robust banking application that handles core financial operations.

**Architecture:**
```
banking
├── entities/
│   ├── Account.java          # Account entity 
│   └── Transaction.java      # Transaction entity
├─ Main.java                  # scenarios
└── test/
    └── java/
        └── BankingTest.java  # Test file
```
### Test 2: Hotel Reservation System
A hotel management system with clean separation of concerns following SOLID principles.

**Architecture:**
```
hotel
├── entities/
│   ├── Room.java          # Room entity (number, type, price)
│   ├── User.java          # User entity (id, balance)
│   ├── RoomType.java      # Enum (STANDARD, JUNIOR, SUITE)
│   └── Booking.java       # Booking entity with historical data
├── services/
│   ├── Service.java      # Hotel management
├── Main.java             # scenarios
└── test/
    └── java/
        └── HotelTest.java  # Test file
```

---
**Author:** Ayman Gassi  
**Date:** December 2025  
**Contact:** aymanegassi972003@gmail.com
