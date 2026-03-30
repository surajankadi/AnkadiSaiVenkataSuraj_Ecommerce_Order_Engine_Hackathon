Title : E-Commerce Order Engine (CLI-Based)

-->Project Overview:

This project is a console-based E-Commerce Order Management System built using Java. It simulates the core functionalities of an online shopping platform such as product management, cart operations, order processing, and payment handling.

The goal of this project is to demonstrate clean architecture principles, loose coupling between services, basic concurrency handling, and complete order lifecycle management.

The system follows a modular service-based approach, where each component such as Product, Cart, Order, and Payment is handled independently.

--> Features Implemented:

1. Product Management:

* Add new products
* View all available products
* Low stock alerts based on threshold

2. Cart Operations:

* Add items to cart with stock reservation
* Remove items from cart
* View cart items and total amount
* Apply coupon discounts

3. Order Management:

* Place order from cart
* Payment simulation with success or failure
* Cancel order
* Return order
* View all orders with status

4. Payment Simulation:

* Random success or failure using Random class
* Handles rollback by restoring stock on failure

5. Logging System:

* Centralized logging using AuditLogger
* Tracks important system activities

6. System Features:

* CLI-based interactive menu
* Failure simulation support
* Basic concurrent user simulation using multithreading

--> Design Approach:

The project follows a layered and loosely coupled architecture.

1. Model Layer:

Contains core entities such as Product, CartItem, and Order. These represent the data structure of the system.

2. Service Layer:

Includes ProductService, CartService, OrderService, and PaymentService. Each service handles specific business logic and follows the single responsibility principle.

3. Utility Layer:

AuditLogger is used to track system events and maintain logs.

4. Main Layer:

Main.java acts as the entry point and provides a command-line interface for user interaction.

--> Key Design Principles Used:

* Encapsulation using private fields and getters
* Single Responsibility Principle
* Loose Coupling between components
* Thread Safety using synchronized methods for stock handling
* Fail-safe mechanisms such as stock rollback on payment failure

--> Assumptions:

* The system supports only a single user (USER_1)
* Data is stored in memory, no database is used
* Coupon functionality is basic with fixed percentage discounts
* Order IDs are generated using UUID
* Payment is simulated and not connected to any real gateway
* Order cancellation and return use index-based identification
* Concurrent simulation is simplified and does not use shared state

--> How to Run the Project:

1. Prerequisites:

* Java JDK 8 or above
* Any IDE such as Eclipse, IntelliJ, or VS Code

2. Steps:

1. Clone the repository
   git clone <your-repo-link>

2. Navigate to the project folder
   cd ecommerce_engine

3. Compile all Java files
   javac *.java

4. Run the application
   java Main

--> Sample CLI Menu:

===== E-Commerce CLI =====

1. Add Product
2. View Products
3. Add to Cart
4. Remove from Cart
5. View Cart
6. Apply Coupon
7. Place Order
8. Cancel Order
9. View Orders
10. Low Stock Alert
11. Return Product
12. Simulate Concurrent Users
13. View Logs
14. Trigger Failure Mode
15. Exit

--> Conclusion:

This project demonstrates a mini e-commerce backend system with a clean structure, real-world workflow from cart to order and payment, proper error handling, and logging support. It is designed to be simple yet extendable for future enhancements.

--> Future Enhancements:

* Integration with databases such as MySQL or MongoDB
* Building REST APIs using Spring Boot
* Adding authentication and multiple user support
* Integration with real payment gateways
* Advanced concurrency handling and performance improvements

This project is useful for understanding system design basics and preparing for backend development interviews.
