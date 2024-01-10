package view;

import controller.StateManager;

import java.util.Scanner;

public class AdminMain_Page implements State {

    @Override
    public void onEnter() {

    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the Admin Center!");
        System.out.println("1. Manage product inventory");
        System.out.println("2. Generate analysis report");
        System.out.println("3. Provide discount");
        //System.out.println("4. Provide promotion");
        System.out.println("4. Quit");

        int option = scanner.nextInt();
        if(option == 1) {
            StateManager.setState("manage");
        }
        else if(option == 2) {
            StateManager.setState("report");
        }
        else if(option == 3) {
            StateManager.setState("discount");
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
