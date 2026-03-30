package ecommerce_engine;

import java.util.*;

public class CartService {

    private Map<String, List<CartItem>> carts = new HashMap<>();
    private Map<String, Double> coupons = new HashMap<>();

    public CartService() {
        coupons.put("SAVE10", 0.10);
        coupons.put("SAVE20", 0.20);
    }

    public void addToCart(String userId, Product product, int qty) {
        if (!product.reserveStock(qty)) {
            System.out.println("Insufficient stock");
            return;
        }

        carts.putIfAbsent(userId, new ArrayList<>());
        List<CartItem> cart = carts.get(userId);

        for (CartItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + qty);
                AuditLogger.log("Updated quantity for " + product.getName());
                return;
            }
        }

        cart.add(new CartItem(product, qty));
        AuditLogger.log("Added " + product.getName() + " to cart");
    }

    public void removeFromCart(String userId, int productId) {
        List<CartItem> cart = carts.get(userId);
        if (cart == null) return;

        cart.removeIf(item -> item.getProduct().getId() == productId);
        AuditLogger.log("Removed product " + productId + " from cart");
    }

    public void viewCart(String userId) {
        List<CartItem> cart = getCart(userId);

        if (cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        double total = 0;

        for (CartItem item : cart) {
            System.out.println(item.getProduct().getName() +
                    " x " + item.getQuantity() +
                    " = " + item.getTotal());
            total += item.getTotal();
        }

        System.out.println("Total: " + total);
    }

    public double getCartTotal(String userId) {
        return getCart(userId).stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

    public void applyCoupon(String userId, String code) {
        double total = getCartTotal(userId);

        if (!coupons.containsKey(code)) {
            System.out.println("Invalid coupon!");
            return;
        }

        double discount = coupons.get(code);
        double finalAmount = total - (total * discount);

        System.out.println("Final Amount after discount: " + finalAmount);
        AuditLogger.log("Coupon applied: " + code);
    }

    public List<CartItem> getCart(String userId) {
        return carts.getOrDefault(userId, new ArrayList<>());
    }

    public void clearCart(String userId) {
        carts.remove(userId);
    }
}