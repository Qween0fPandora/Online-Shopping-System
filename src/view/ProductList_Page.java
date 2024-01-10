package view;

import controller.Global;
import controller.StateManager;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductList_Page implements State {
    public ArrayList<Product> productList;

    @Override
    public void onEnter() {
        Global.server.connectProductTable("Products");
        productList = Global.server.getAllProduct();
    }

    @Override
    public void run(Scanner scanner) {
        System.out.println();
        System.out.println("--------------------------------------------");
        Product.printProductList(productList);
        System.out.println("--------------------------------------------");
        System.out.println("Please choose one of the following options:");
        System.out.println("1.See product details");
        System.out.println("2.Go back to the main page");
        System.out.println("3.Quit");
        int option = scanner.nextInt();
        if(option == 1){
            System.out.println("Please enter the product id you want to see:");
            int id = scanner.nextInt();
            for (Product product : productList) {
                if (product.getId() == id) {
                    Global.currentProduct = product;
                    StateManager.setState("details");
                }
            }

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
