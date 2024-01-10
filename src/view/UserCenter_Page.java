package view;

import controller.Global;
import controller.StateManager;
import model.Customer;

import java.util.Objects;
import java.util.Scanner;

public class UserCenter_Page implements State{

    @Override
    public void onEnter() {
        Global.server.connectCostumerTable("Customer");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the User Center!");

        System.out.println("1. Set your email");
        scanner.nextLine();
        String email = scanner.nextLine();

        // Wait for user input before moving on


        System.out.println("2. Set your password");
        String password = scanner.nextLine();

        // Wait for user input before moving on
        System.out.println("3. Set your name");
        String name = scanner.nextLine();

        // Wait for user input before moving on

        System.out.println("4. Set your detail address");
        String detail = scanner.nextLine();

        // Wait for user input before moving on

        System.out.println("5. Set your city");
        String city = scanner.nextLine();

        // Wait for user input before moving on

        System.out.println("6. Set your country");
        String country = scanner.nextLine();
        Customer result = Global.server.queryCostumerTable(email);

        if (Objects.equals(result.getEmail(), email)){
            System.out.println("The email is registered already!");
            StateManager.setState("check");
        }else{
            Customer customer = new Customer(email, password, name, detail, city, country);

            Global.server.insertCostumerTable(customer);

            System.out.println("Your information is saved!");

            Global.customer = customer;

            StateManager.setState("main");
        }
    }

    @Override
    public void onExit() {

    }
}
