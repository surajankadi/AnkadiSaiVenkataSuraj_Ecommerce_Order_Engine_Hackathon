package ecommerce_engine;

import java.util.*;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private PaymentService paymentService = new PaymentService();

    public void placeOrder(String userId, CartService cartService) {
        List<CartItem> cart = cartService.getCart(userId);

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double total = cart.stream().mapToDouble(CartItem::getTotal).sum();

        Order order = new Order(UUID.randomUUID().toString(), cart, total);

        boolean paymentSuccess = paymentService.processPayment();

        if (!paymentSuccess) {
            for (CartItem item : cart) {
                item.getProduct().releaseStock(item.getQuantity());
            }
            order.setStatus("FAILED");
            System.out.println("Payment failed");
        } else {
            order.setStatus("PAID");
            cartService.clearCart(userId);
            System.out.println("Order placed successfully");
        }

        orders.add(order);
        AuditLogger.log("Order created: " + order.getOrderId());
    }

    public void cancelOrder(int index) {
        if (index < 0 || index >= orders.size()) {
            System.out.println("Invalid Order ID");
            return;
        }

        Order order = orders.get(index);
        order.setStatus("CANCELLED");
        AuditLogger.log("Order cancelled: " + order.getOrderId());
    }

    public void returnProduct(int index) {
        if (index < 0 || index >= orders.size()) {
            System.out.println("Invalid Order ID");
            return;
        }

        Order order = orders.get(index);
        order.setStatus("RETURNED");
        AuditLogger.log("Order returned: " + order.getOrderId());
    }

    public void viewOrders() {
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(i + " -> " + orders.get(i));
        }
    }
}