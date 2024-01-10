package view;


import controller.Global;
import controller.StateManager;

import java.util.Scanner;

public class Discount_Page implements State{
    @Override
    public void onEnter() {
        Global.server.connectCostumerTable("Customer");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the Discount Distribution Center!");
        System.out.println("1. Distribute the discount to a customer");
        //System.out.println("2. Set discount to a product");
        System.out.println("2. Back to main page");

        int option = scanner.nextInt();
        if(option == 1) {
            System.out.println("Input the customer email to send him/her the info about gaining a coupon");
            System.out.print("Discount: ");
            String discount = scanner.nextLine();
            scanner.nextLine();  // Consume the remaining newline
            System.out.print("Customer Email: ");
            String email = scanner.nextLine();



            System.out.println("The coupon with a discount: " + discount + " has been distributed to user: " + email + " successfully.");
        }
        else {
            StateManager.setState("admin_main");
        }

    }

    @Override
    public void onExit() {

    }
}