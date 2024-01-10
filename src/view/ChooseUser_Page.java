package view;

import controller.StateManager;

import java.util.Scanner;

public class ChooseUser_Page implements State{
    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to our online shopping system!");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Check in as customer");
        System.out.println("2. Check in as administrator");
        System.out.println("3. Exit");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            StateManager.setState("check");
        } else if (choice.equals("2")) {
            StateManager.setState("admin");
        } else if (choice.equals("3")) {
            System.out.println("Thank you for using our system!");
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    @Override
    public void onExit() {

    }
}
