package view;

import controller.Global;
import controller.StateManager;
import model.Customer;

import java.util.Objects;
import java.util.Scanner;

public class CheckIn_Page implements State{

    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the CheckIn page!");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        if(choice == 1) {
            StateManager.setState("center");
        }
        else if(choice == 2)
        {
            scanner.nextLine();
            System.out.println("Email: ");
            String email = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            Customer result = Global.server.queryCostumerTable(email);

            if (Objects.equals(result.getEmail(), email) && Objects.equals(result.getPassword(), password)) {
                System.out.println("Logged in!");
                Global.customer = result;
                StateManager.setState("main");
            }
            else {
                System.out.println("Invalid email or password. Please try again.");
            }
        }
        else
        {
            System.out.println("Thank you for using our system!");
            System.exit(0);
        }

    }

    @Override
    public void onExit() {

    }
}
