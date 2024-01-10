package model;

import java.util.ArrayList;

public class Product {
    private int id ;
    private String name ;
    private double price ;
    private int stock ;
    private int quantity;
    private String description;
    private String specs;
    private String brand ;
    private String category ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Product(int id, String name, double price, int stock, int quantity, String description, String specs, String brand, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.quantity = quantity;
        this.description = description;
        this.specs = specs;
        this.brand = brand;
        this.category = category;
    }

    public static void printProductList(ArrayList<Product> productList){
        for(Product product : productList) {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("--------------------------");
        }
    }


    public static void printCart(ArrayList<Product> productList){
        for(Product product : productList){
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("--------------------------");
        }
    }
    public static void printProduct(Product product){
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("--------------------------");
    }
}