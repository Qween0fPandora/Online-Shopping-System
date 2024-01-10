package view;

import controller.Global;
import controller.StateManager;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductFilter_Page implements State{

    @Override
    public void onEnter() {
        Global.server.connectProductTable("Products");
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to the product filter page!");
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Filter by category");
        System.out.println("2. Filter by brand");
        System.out.println("3. Filter by price");
        System.out.println("4. Go back to the main page");
        System.out.println("5. Quit");

        int option = scanner.nextInt();
        scanner.nextLine();
        if(option == 1){
            System.out.println("Please enter the category you want to filter:");
            String category = scanner.nextLine();
            ArrayList<Product> result = Global.server.filterProduct(category,"",0,1000000);
            Product.printProductList(result);
            Global.searchResult = result;
            StateManager.setState("select");
        }
        else if(option == 2){
            System.out.println("Please enter the brand you want to filter:");
            String brand = scanner.nextLine();
            ArrayList<Product> result = Global.server.filterProduct("",brand,0,1000000);
            Product.printProductList(result);
            Global.searchResult = result;
            StateManager.setState("select");
        }
        else if(option == 3){
            System.out.println("Please enter the minimum price you want to filter:");
            double min = scanner.nextDouble();
            System.out.println("Please enter the maximum price you want to filter:");
            double max = scanner.nextDouble();
            ArrayList<Product> result = Global.server.filterProduct("","",min,max);
            Product.printProductList(result);
            Global.searchResult = result;
            StateManager.setState("select");
        }
        else if(option == 4){
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
