package ecommerce_engine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService();
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService(); // if exists

        String userId = "USER_1";

        while (true) {
            System.out.println("\n===== E-Commerce CLI =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Add to Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Apply Coupon");
            System.out.println("7. Place Order");
            System.out.println("8. Cancel Order");
            System.out.println("9. View Orders");
            System.out.println("10. Low Stock Alert");
            System.out.println("11. Return Product");
            System.out.println("12. Simulate Concurrent Users");
            System.out.println("13. View Logs");
            System.out.println("14. Trigger Failure Mode");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    productService.addProduct(new Product(id, name, price, stock));
                    break;

                case 2:
                    productService.viewProducts();
                    break;

                case 3:
                    System.out.print("Product ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Qty: ");
                    int qty = sc.nextInt();

                    Product product = productService.getProduct(pid);
                    if (product != null) {
                        cartService.addToCart(userId, product, qty);
                    }
                    break;

                case 4:
                    System.out.print("Product ID to remove: ");
                    int removeId = sc.nextInt();
                    cartService.removeFromCart(userId, removeId);
                    break;

                case 5:
                    cartService.viewCart(userId);
                    break;

                case 6:
                    System.out.print("Enter Coupon Code: ");
                    String coupon = sc.next();
                    cartService.applyCoupon(userId, coupon);
                    break;

                case 7:
                    orderService.placeOrder(userId, cartService);
                    break;

                case 8:
                    System.out.print("Enter Order ID to cancel: ");
                    int cancelId = sc.nextInt();
                    orderService.cancelOrder(cancelId);
                    break;

                case 9:
                    orderService.viewOrders();
                    break;

                case 10:
                    productService.lowStockAlert();
                    break;

                case 11:
                    System.out.print("Enter Order ID for return: ");
                    int returnId = sc.nextInt();
                    orderService.returnProduct(returnId);
                    break;

                case 12:
                    ConcurrentUserSimulator.simulate(); // create this class
                    break;

                case 13:
                    AuditLogger.showLogs();
                    break;

                case 14:
                    FailureSimulator.trigger(); // create this class
                    break;

                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}