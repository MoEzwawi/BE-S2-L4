package MoEzwawi.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private final long id;
    private String status;
    private final LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private final Customer customer;

    public Order(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
        Random rndm = new Random();
        this.id= rndm.nextLong(10000,99999);
        this.orderDate = LocalDate.now();
        this.deliveryDate = orderDate.plusDays(3);
        this.status = "Processing";
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", customer=" + customer +
                '}';
    }
}