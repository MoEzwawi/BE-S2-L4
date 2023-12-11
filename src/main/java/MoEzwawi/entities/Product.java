package MoEzwawi.entities;

import java.util.Random;

public class Product {
    private final long id;
    private String name;
    private Category category;
    private Double price;

    public Product(String name, Category category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
        Random rndm = new Random();
        this.id= rndm.nextLong(10000,99999);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: "+System.lineSeparator()+"{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}'+System.lineSeparator();
    }
}