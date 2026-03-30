package ecommerce_engine;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int reservedStock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reservedStock = 0;
    }

    public synchronized boolean reserveStock(int qty) {
        if (stock >= qty) {
            stock -= qty;
            reservedStock += qty;
            return true;
        }
        return false;
    }

    public synchronized void releaseStock(int qty) {
        stock += qty;
        reservedStock -= qty;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return id + " | " + name + " | ₹" + price + " | Stock: " + stock;
    }
}