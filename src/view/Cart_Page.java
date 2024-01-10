package view;

import controller.Global;
import controller.StateManager;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class Cart_Page implements State{
    @Override
    public void onEnter() {
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the cart page!");
        System.out.println("Here is your cart:");
        System.out.println("--------------------------------------------");
        ArrayList<Product> result = Global.server.getCart();

        Product.printCart(result);
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Make an order!");
        System.out.println("2. Change the quantity of a product");
        System.out.println("3. Go back to the main page");
        System.out.println("4. Quit");

        String option = scanner.nextLine();
        switch (option){
            case"1":
                Global.buyNow = false;
                StateManager.setState("order");
                break;
            case"2":
                System.out.println("Which product do you want to change? Please enter the product ID");
                int pid = scanner.nextInt();
                scanner.nextLine();
                System.out.println("How many do you want to buy?");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                String email = Global.customer.getEmail();
                Global.server.changeCartProduct(email, pid, quantity);
                break;
            case"3":
                StateManager.setState("main");
                break;
            case"4":
                System.out.println("Thank you for using our system!");
                System.exit(0);
                break;
        }


    }

    @Override
    public void onExit() {
        Global.buyNow = false;
    }
}
