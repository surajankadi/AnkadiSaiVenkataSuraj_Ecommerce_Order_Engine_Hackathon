package ecommerce_engine;

public class ConcurrentUserSimulator {

    public static void simulate() {

        System.out.println("Simulating concurrent users...\n");

        Runnable userTask = () -> {
            String userId = Thread.currentThread().getName();

            ProductService productService = new ProductService();
            CartService cartService = new CartService();

            Product p = new Product(1, "Phone", 50000, 10);
            productService.addProduct(p);

            cartService.addToCart(userId, p, 1);

            System.out.println(userId + " added product to cart");
        };

        Thread t1 = new Thread(userTask, "USER_A");
        Thread t2 = new Thread(userTask, "USER_B");
        Thread t3 = new Thread(userTask, "USER_C");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nSimulation completed.");
    }
}