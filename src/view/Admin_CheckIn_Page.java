package view;

import controller.Global;
import controller.StateManager;
import model.Admin;

import java.util.Objects;
import java.util.Scanner;

public class Admin_CheckIn_Page implements State {
    @Override
    public void onEnter() {
        Global.server.connectAdminTable("Admin");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the CheckIn page! (Administrator Portal)");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Log-in");
        System.out.println("2. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1)
        {
            System.out.println("Email: ");
            String email = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            Admin result = Global.server.queryAdminTable(email);
            if (Objects.equals(result.getEmail(), email) && Objects.equals(result.getPassword(), password)) {
                System.out.println("Logged in!");
                Global.admin = result;
                StateManager.setState("admin_main");
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
