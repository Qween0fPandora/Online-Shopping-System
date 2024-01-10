package model;

import java.util.ArrayList;

public class Review {
    private String name;
    private String review;
    private int productId;

    public Review(String name, String review, int productId) {
        this.name = name;
        this.review = review;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public static void showReview(ArrayList<Review> reviews) {
        for (Review r : reviews) {
            System.out.println("Name: " + r.getName());
            System.out.println("Review: " + r.getReview());
            System.out.println("--------------------------");
        }

    }
}
