package view;

import controller.Global;
import controller.StateManager;
import model.Order;
import model.Product;
import model.Review;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Product_Details_Page implements State{
    public static void printDetails(Product product) {
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Specs: " + product.getSpecs());
        System.out.println("Brand: " + product.getBrand());
        System.out.println("Category: " + product.getCategory());
        System.out.println("-----------------------");
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        printDetails(Global.currentProduct);
        System.out.println("--------------------------------------------");
        System.out.println("Please select an option:");

        System.out.println("1. Buy now!");
        System.out.println("2. Add to cart");
        System.out.println("3. Go back to the main page");
        System.out.println("4. Have a look at customer reviews");
        System.out.println("5. Write review");
        System.out.println("6. Quit");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.print("Quantity for " + Global.currentProduct.getName() + ": ");
                int q = scanner.nextInt();
                scanner.nextLine();
                Global.currentProduct.setQuantity(q);
                Global.buyNow = true;
                StateManager.setState("order");
                break;
            case "2":
                System.out.println("How many do you want to buy?");
                 int quantity = scanner.nextInt();
                 scanner.nextLine();
                 int pid = Global.currentProduct.getId();
                 Global.server.insertToCart(pid, quantity);
                 System.out.println("Added to cart!");
                 break;
            case "3":
                StateManager.setState("main");
                break;
            case "4":
                ArrayList<Review> reviews = Global.server.getReview(Global.currentProduct.getId());
                Review.showReview(reviews);
                break;
            case "5":
                boolean hasPurchased = false;
                ArrayList<Order> orders = Global.server.getOrder();
                for (Order order : orders) {
                    if(Objects.equals(order.getEmail(), Global.customer.getEmail())) {
                        hasPurchased = true;
                        break;
                    }
                }
                if(hasPurchased) {
                    System.out.print ("Review: ");
                    String review = scanner.nextLine();
                    int code = Global.server.insertReview(new Review(Global.customer.getName(), review, Global.currentProduct.getId()));

                    if(code == 1) {
                        System.out.println("Review uploaded successfully");
                    }
                    else {
                        System.out.println("Failed to write review");
                    }
                }
                else {
                    System.out.println("Please buy the product first!");
                }
                break;
            case "6":
                System.out.println("Thank you for using our system!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                break;
        }
    }

    @Override
    public void onExit() {
/*        Global.buyNow = false;*/
    }
}
