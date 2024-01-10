package view;

import controller.Global;
import controller.StateManager;
import model.Product;

import java.util.Scanner;

public class ProductSearch_Page implements State{
    @Override
    public void onEnter() {
        Global.server.connectProductTable("Products");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println("Please enter the product id you want to search:");
        scanner.nextLine();
        String productId = scanner.nextLine();
        Product product = Global.server.searchProduct(productId);
        Product.printProduct(product);

        System.out.println("Please choose one of the following options:");
        System.out.println("1. Go to see details");
        System.out.println("2. Go back to the main page");
        System.out.println("3. Quit");
        int option = scanner.nextInt();
        scanner.nextLine();
        if(option == 1){
            Global.currentProduct = product;
            StateManager.setState("details");
        }
        else if(option == 2){
            StateManager.setState("main");
        }
        else{
            System.out.println("Thank you for using our system!");
            System.exit(0);
        }
    }

    @Override
    public void onExit() {

    }
}
