package ecommerce_engine;

import java.util.*;

public class ProductService {

    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
        AuditLogger.log("Product added: " + product.getName());
    }

    public void viewProducts() {
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public void lowStockAlert() {
        for (Product p : products.values()) {
            if (p.getStock() < 5) {
                System.out.println("Low stock: " + p.getName());
            }
        }
    }
}