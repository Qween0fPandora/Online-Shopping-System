package view;

import controller.SQL;
import controller.StateManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Main_Page implements State{

    public static void MainPage(SQL server) throws SQLException {
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the main page!");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. List all the products");
        System.out.println("2. Search for a product");
        System.out.println("3. Filter products");
        System.out.println("4. Go to the shopping cart");
        System.out.println("5. To to the order history");
        System.out.println("6. Log-out");
        System.out.println("7. Quit");

        int option = scanner.nextInt();
        scanner.nextLine();
        if(option == 1) {
            StateManager.setState("list");
        }
        else if(option == 2) {
            StateManager.setState("search");
        }
        else if(option == 3) {
            StateManager.setState("filter");
        }
        else if(option == 4) {
            StateManager.setState("cart");
        }
        else if(option == 5) {
            StateManager.setState("history");
        }
        else if (option == 6) {
            StateManager.setState("check");
        } else {
            System.out.println("Thank you for using our system!");
            System.exit(0);
        }
    }

    @Override
    public void onExit() {

    }
}
