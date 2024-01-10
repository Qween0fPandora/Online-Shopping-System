package view;

import controller.Global;
import controller.StateManager;

import java.util.Scanner;

public class Manage_Product_Page implements State{
    @Override
    public void onEnter() {
        Global.server.connectProductTable("products");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the Manage Product Center!");
        System.out.println("1. Add a product");
        System.out.println("2. Delete a product");
        System.out.println("3. Update the stock level");
        System.out.println("4. Back to main page");

        int option = scanner.nextInt();
        if(option == 1) {
            StateManager.setState("add");
        }
        else if(option == 2) {
            StateManager.setState("delete");
        }
        else if(option == 3) {
            StateManager.setState("update");
        }
        else {
            System.out.println("Thank you for using our system!");
            System.exit(0);
        }
    }

    @Override
    public void onExit() {

    }
}
